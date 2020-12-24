package com.yun.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 多类型数据库混合数据源连接池
 * 数据源配置分别需要提供：
 * type，datasourceId，connStr，userName,pwd
 */
public class MixedConnectionPool implements IDBConnectionPool{
    private static Logger LOGGER = LoggerFactory.getLogger(MixedConnectionPool.class);
    /**
     * 数据源配置
     */
    private Map<String, HashMap<String, String>> datasourceConfig = new HashMap<>(4);

    /**
     * 多数据源连接对象池集合
     */
    private final ConcurrentHashMap<String, LinkedBlockingQueue<Connection>> connectionPool = new ConcurrentHashMap<>(4);
    /**
     * 数据源对象池连接使用中数量计数
     */
    private ConcurrentHashMap<String, AtomicInteger> connectionPoolActivedCount = new ConcurrentHashMap<>(4);

    /**
     * 待验证连接有效性的连接的集合队列
     */
    private final LinkedBlockingQueue<Connection> connectionCheckValidQueue = new LinkedBlockingQueue<>(64) ;

    /**
     * 连接数据源计数可重入锁
     */
    private ConcurrentHashMap<String, ReentrantLock > connectionCountLockMap = new ConcurrentHashMap<>(4);

    /**
     * 待验证连接有效性定时器
     */
    private Timer checkValidTimer ;
    /**
     * 重建连接线程池
     */
    private ExecutorService rebuildConnectionExecutor = Executors.newFixedThreadPool(2);
    
    /**
     * 每个数据源连接池大小，默认4
     */
    private int connectQueueSize = 4;
    /**
     * 是否关闭连接池
     */
    private volatile boolean isClosed = false;
    /**
     * 默认获取连接超时时间 ，单位毫秒
     */
    private long timeoutMs = 5000;

    /**
     * 默认返回的时间字段时间格式器 yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public MixedConnectionPool(int connectQueueSize, long timeoutMs) {
        this.connectQueueSize = connectQueueSize;
        this.timeoutMs = timeoutMs;
        init();
    }

    public MixedConnectionPool(int connectQueueSize, long timeoutMs, String type, String datasourceId, String connStr,
            String userName, String pwd) {
        this.connectQueueSize = connectQueueSize;
        this.timeoutMs = timeoutMs;
        String key = type + "-" + datasourceId;
        if (connectionCountLockMap.get(key) == null ) {
            ReentrantLock countLock = new ReentrantLock();
            connectionCountLockMap.putIfAbsent(key, countLock);
        }
        // 初始化使用中连接计数器
        if (connectionPoolActivedCount.get(key) == null ){
            AtomicInteger actived = new AtomicInteger(0);
            actived = connectionPoolActivedCount.putIfAbsent(key, actived);
        }
        // 初始化数据源连接
        for (int i = 0; i < connectQueueSize; i++) {
            initConnection(connectionPool, type, datasourceId, connStr, userName, pwd);
        }

        logDebug(">>>>> MixedConnectionPool after inited [type={},datasourceId={}] current idleAcount={},activedCount={} ",
                type, datasourceId, this.getIdleCount(type, datasourceId), this.getActivedCount(type, datasourceId));
        init();
    }

    // private ThreadLocal<Map<String,Connection>> localConnections= new ThreadLocal<>();

    public void init() {

        // 初始化连接检查定时任务线程
        checkValidTimer = new Timer();
        checkValidTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {

                logDebug( ">>>>> MixedConnectionPool checkvalidTask  begin check connection  "  );
                while(true) {
                    
                    Connection conn = connectionCheckValidQueue.poll();

                    if (conn != null ){
                        if(!checkValidConnection(conn)){
                            try {
                                conn.close();
                                //conn =null ; 对象实例会被放回队列而被获取到，而清除
                            } catch (Exception e) {
                                LOGGER.error("><><> MixedConnectionPool checkvalidTask[{}] ,close connection  exception: ", conn,e);
                            }
                            LOGGER.error( ">>>>> MixedConnectionPool checkvalidTask conn[{}]  was not valid,so close and release resource ",conn  );
                        }else {
                            logDebug( ">>>>> MixedConnectionPool checkvalidTask conn[{}]  was valid  ... ",conn  );
                        }
                    } else {
                        break;
                    }
                }
                logDebug( "<<<<< MixedConnectionPool checkvalidTask end check over ..." );

            }
            
        }, 3000,5000);

        logDebug(">>>>> MixedConnectionPool with{}] inited",this);
    }

    private void logDebug(String format,Object ...args){
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(format,args);
        }
    }
    /**
     * 实例化后添加数据源
     * @see MixedConnectionPool::initConnection
     * @param type
     * @param datasourceId
     * @param connStr
     * @param userName
     * @param pwd
     */
    public void addDataSource(String type, String datasourceId, String connStr,
    String userName, String pwd){
        String key = type + "-" + datasourceId;
        if (connectionCountLockMap.get(key) == null ) {
            ReentrantLock countLock = new ReentrantLock();
            connectionCountLockMap.putIfAbsent(key, countLock);
        }
        if (connectionPoolActivedCount.get(key) == null ){
            AtomicInteger actived = new AtomicInteger(0);
            actived = connectionPoolActivedCount.putIfAbsent(key, actived);
        }
        for (int i = 0; i < connectQueueSize; i++) {
            initConnection(connectionPool, type, datasourceId, connStr, userName, pwd);
        }
    }

    /**
     * 放置空闲连接到对应数据源连接池
     * @param  type
     * @param  datasourceId
     * @param  conn
     * @return boolean
     */
    private boolean putIdleConnection(String type, String datasourceId, Connection conn) {
        logDebug(">>>>> putIdleConnection[type={},datasourceId={}] current idleAcount={},activedCount={} ", type,
                datasourceId, this.getIdleCount(type, datasourceId), this.getActivedCount(type, datasourceId));
        boolean flag = putConnection(connectionPool, type, datasourceId, conn);
        logDebug("<<<<< putIdleConnection[type={},datasourceId={}] current idleAcount={},activedCount={} ", type,
                datasourceId, this.getIdleCount(type, datasourceId), this.getActivedCount(type, datasourceId));
        return flag;

    }

    /**
     * 放置连接到对应数据源连接池的阻塞队列
     * @param  connectionPool 数据源连接池
     * @param  type           数据库类型 mysql,clickhouse 
     * @param  datasourceId   数据源编号
     * @param  conn           数据源连接对象实例
     * @return boolean
     */
    private boolean putConnection(ConcurrentHashMap<String, LinkedBlockingQueue<Connection>> connectionPool, String type,
            String datasourceId, Connection conn) {    
        String key = type + "-" + datasourceId;
        if (connectionPool.containsKey(key)) {
            if (connectionPool.get(key).size() < connectQueueSize) {
                if (isValidConnection(conn)){
                    connectionPool.get(key).offer(conn);
                    logDebug(">>>>> putConnection connection: {} sucess", conn);
                    return true;    
                } 
            }
        } else {
            LinkedBlockingQueue<Connection> dbqueue = new LinkedBlockingQueue<>(connectQueueSize);
            connectionPool.putIfAbsent(datasourceId, dbqueue);
            if (isValidConnection(conn)){
                dbqueue.offer(conn);
                logDebug(">>>>> putConnection new dequeue connection: {} , sucess", conn);
                return true;
            }
        }
        LOGGER.error("><><> putConnection connection: {} , fail may be it is not valid", conn);
        return false;

    }
    /**
     * 初始化数据源连接对象到连接池
     * @param  connectionPool 数据源连接池
     * @param  type           数据库类型 mysql,clickhouse 
     * @param  datasourceId   数据源编号
     * @param  connStr        数据库连接字符串
     * @param  userName       数据库用户名
     * @param  pwd            数据库用户密码
     */
    public void initConnection(ConcurrentHashMap<String, LinkedBlockingQueue<Connection>> connectionPool, String type,
            String datasourceId, String connStr, String userName, String pwd) {

        if (DBTypeEnum.mysql.name().equals(type) || DBTypeEnum.clickhouse.name().equals(type)) {
            String key = type + "-" + datasourceId;
            if (connectionPool.containsKey(key)) {
                if (connectionPool.get(key).size() < connectQueueSize) {
                    Connection conn = buildConnection(type, datasourceId, connStr, userName, pwd);
                    if (isValidConnection(conn)){
                        connectionPool.get(key).offer(conn);
                    }
                        
                }
            } else {
                Connection conn = buildConnection(type, datasourceId, connStr, userName, pwd);

                LinkedBlockingQueue<Connection> dbqueue = new LinkedBlockingQueue<>(connectQueueSize);
                connectionPool.putIfAbsent(key, dbqueue);
                if (isValidConnection(conn)){
                    dbqueue.offer(conn);
                }
                    

            }
        } else {
            // just for mysql and clickhouse,other do nothing ...
        }

    }
    
    /**
     *   根据参数创建对应的数据库连接对象
     * @see DBTypeEnum 
     * @see Connection
     * @param  type         数据库类型 mysql,clickhouse 
     * @param  datasourceId 数据源编号
     * @param  connStr      数据库连接字符串
     * @param  userName     数据库用户名
     * @param  pwd          数据库用户密码
     * @return Connection   返回数据库连接    
     */
    @Override
    public Connection buildConnection(String type, String datasourceId, String connStr, String userName, String pwd) {
        Connection conn = null;
        try {
            if (DBTypeEnum.mysql.name().equals(type)) {
                Class.forName("com.mysql.jdbc.Driver");

            } else if (DBTypeEnum.clickhouse.name().equals(type)){
                Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
            } else {
                // TODO:
                // do nothing ....
                throw new UnsupportedOperationException("buildConnection type[" + type + "] was not supported...");
            }
            // 获取连接
            conn = DriverManager.getConnection(connStr, userName, pwd);
            HashMap<String, String> config = new HashMap<>(6);
            config.put("type", type);
            config.put("datasourceId", datasourceId);
            config.put("connStr", connStr);
            config.put("userName", userName);
            config.put("pwd", pwd);
            datasourceConfig.put(type + "-" + datasourceId, config);

        } catch (SQLException e) {
            LOGGER.error("><><> buildConnection exception: ", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("><><> buildConnection exception: ", e);
        } catch (Exception e) {
            LOGGER.error("><><> buildConnection exception: ", e);
        }
        return conn;
    }
    /**
     * 获取对应数据源连接池的连接对象
     * @param  type           数据库类型 mysql,clickhouse 
     * @param  datasourceId   数据源编号
     * @param  timeout        获取连接超时时间，默认毫秒，如果 0 则线程阻塞等待
     * @return Connection     返回数据库连接 
     */
    @Override
    public Connection getConnection(String type, String datasourceId, long timeout) {
        Connection conn = null;
        if (!this.isClosed) {

            logDebug(">>>>> begin getConnection[type={},datasourceId={}] current idleAcount={},activedCount={} ",
                    type, datasourceId, this.getIdleCount(type, datasourceId),
                    this.getActivedCount(type, datasourceId));

           final String key = type + "-" + datasourceId;
            if (connectionPool.containsKey(key)) {
                try {
                    if (timeout > 0) {
                        conn = connectionPool.get(key).poll(timeout, TimeUnit.MILLISECONDS);
                        if (conn == null) { // just for timeout
                            LOGGER.error("><><> getConnection[" + key + "] timeout[{}] ,dbpool was busy,please retry after seconds",timeout);
                        }
                    } else {
                        conn = connectionPool.get(key).poll(); // if queue was empty then return null

                        if (conn == null) { // just for queue empty
                            LOGGER.error( "><><> getConnection[" + key + "] queue was empty,dbpool was busy,try rebuild the connection");
                            final int dataSourceQueueSize = connectQueueSize;
                            while(true){ // just for blocking for erver  , connectionPool.get(key).poll() 如果队列为空则直接返回null中断
                                conn = connectionPool.get(key).poll(timeoutMs, TimeUnit.MILLISECONDS); 
                                if (conn != null){
                                    break;
                                }
                                if (!this.isClosed){
                                    rebuildConnectionExecutor.execute( ()-> {  //未拿到连接让重建连接线程执行重建
                                        logDebug( "><><> getConnection[" + key + "] rebuildConnectionExecutor rebuidConnection run command");
                                        final int len =  dataSourceQueueSize - MixedConnectionPool.this.getTotalCount(type, datasourceId) ;
                                        for (int i = 0;i < len ; i++ ) {
                                            boolean rebuildFlag = MixedConnectionPool.this.rebuildAndPutConnection(type, datasourceId);
                                            if ( rebuildFlag ){
                                                MixedConnectionPool.this.logDebug( ">>>>> getConnection [" + key + "] rebuildConnectionExecutor,  queue empty rebuild new connection[{}] into queue --- success",i);
                                            }else {
                                                LOGGER.error( "><><> getConnection[" + key + "] rebuildConnectionExecutor,  queue empty rebuild new connection[{}] into queue --- fail",i);
                                            }
                                        }
                                    });  
                                } else {
                                    return null;
                                }
                                Thread.sleep(5);
                            }
                        }
                    }

                    // if (conn == null) { 
                        
                    //     //未拿到连接容量允许情况下尝试重建连接
                    //     int len = connectQueueSize - this.getTotalCount(type, datasourceId) ;
                    //     if ( len  > 0   ) {
                    //         int pollFlag = 0;
                    //         for (int i = 0;i < len ; i++ ) {
                    //             boolean rebuildFlag = this.rebuildAndPutConnection(type, datasourceId);
                    //             if ( rebuildFlag ){
                    //                 pollFlag = pollFlag + 1;
                    //                 logDebug( ">>>>> getConnection[" + key + "] queue empty rebuild new connection[{}] into queue --- success",i);
                    //             }else {
                    //                 LOGGER.error( "><><> getConnection[" + key + "] queue empty rebuild new connection[{}] into queue --- fail",i);
                    //             }
                    //         }
                    //         if (pollFlag > 0){
                    //             conn = connectionPool.get(key).poll(); 
                    //         }
                            
                    //     }
                    // }

                    if (conn != null){
                        boolean validFlag = isValidConnection(conn);
                        if (validFlag) {
                            // 无论是新创建的还是初始化的，只要从数据源池队列中获取到连接对象，
                            // 都放入另外的检查连接队列由定时任务线程异步检查连接的实际有效性,
                            // 因为连接不是正常情况下可能已经中断，但是isClosed 还是返回假
                            connectionCheckValidQueue.offer(conn);
                            connectionPoolActivedCount.get(key).incrementAndGet();
                        }else {
                            conn = null; // 释放资源
                            LOGGER.error("><><> getConnection[" + key + "] connetion was not valid(closed) ");
                        }

                    }
                    logDebug(">>>>> getConnection[type={},datasourceId={}] current idleAcount={},activedCount={} ", type, datasourceId, this.getIdleCount(type, datasourceId),
                    this.getActivedCount(type, datasourceId));
                } catch (InterruptedException e) {
                    LOGGER.error("><><> getConnection[" + key + "] exception:", e);
                }
            }
            logDebug("<<<<< end   getConnection[type={},datasourceId={}] current idleAcount={},activedCount={} ",
                    type, datasourceId, this.getIdleCount(type, datasourceId),
                    this.getActivedCount(type, datasourceId));
        }
        return conn;
    }
    
    /**
     * 简单判断连接是否关闭
     * @see Connection::isClosed
     * @param   conn
     * @return  boolean
     */
    public boolean isValidConnection(Connection conn) {
        try {
            if (conn == null || conn.isClosed() ) { 
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("><><> isValidConnection[" + conn + "] exception:", e);
            return false;
            
        }
        return true;
    }

    /**
     * 检查判断连接是否有效
     * 连接中途断网经典错误:
     * com.mysql.jdbc.exceptions.jdbc4.CommunicationsException: Communications link failure

        The last packet successfully received from the server was 7,632 milliseconds ago.  The last packet sent successfully to the server was 7,666 milliseconds ago.
        Caused by: java.net.SocketException: Connection reset by peer: socket write error
     * @see Connection::isValid  提交查询验证是否有效较耗时
     * @param   conn
     * @return  boolean
     */
    public boolean checkValidConnection(Connection conn) {
        try {
            if (conn != null  && conn.isValid(500)) {
                return true;
            }
        } catch (Exception e) {
            LOGGER.error("><><> checkValidConnection[" + conn + "] exception:", e);
            return false;
            
        }
        return false;
    }
    /**
     * 重建连接放入连接池
     * @param  type
     * @param  datasourceId
     * @return boolean
     */
    public boolean rebuildAndPutConnection(String type, String datasourceId){
        String key = type + "-" + datasourceId;
        // 容量允许情况下尝试重建连接
        int curTotal = getTotalCount(type,datasourceId);
        if (curTotal <  connectQueueSize ) {
            HashMap<String, String> config = datasourceConfig.get(key);
            Connection newConn = buildConnection(type, datasourceId, config.get("connStr"), config.get("userName"),
            config.get("pwd"));
            //重建连接检查连接实际有效性
            if ( newConn != null && checkValidConnection(newConn)){
                LOGGER.error( "><><> rebuildAndPutConnection[" + key + "] rebuild new connection into queue --- success");
                return putIdleConnection(type, datasourceId, newConn);
                    
            } else {
                LOGGER.error( "><><> rebuildAndPutConnection[" + key + "] rebuild new connection into queue --- fail,please retry after seconds");
                return false;
            }
        }
        LOGGER.error( "><><> rebuildAndPutConnection[" + key + "] current all connection count ={} was not less than connectQueueSize={} ,so give up rebuild connection",curTotal,connectQueueSize);
        return false;
    }
    /**
     * 返还连接对象对应数据源连接池
     * @param  type           数据库类型 mysql,clickhouse 
     * @param  datasourceId   数据源编号
     * @param  conn     返回数据库连接
     */
    @Override
    public boolean releaseConnection(String type, String datasourceId, Connection conn) {
        logDebug(">>>>> releaseConnection[type={},datasourceId={}] begin current idleAcount={},activedCount={} ",
                type, datasourceId, this.getIdleCount(type, datasourceId), this.getActivedCount(type, datasourceId));

        
        String key = type + "-" + datasourceId;
        if (isValidConnection(conn)) {
            boolean result = putIdleConnection(type, datasourceId, conn);
            if (result == false) {
                logDebug(">>>>> releaseConnection[type={},datasourceId={}] connection=[{}] put idle fail,maybe it was closed or not valid ", type,datasourceId,conn);
            }
        } else {
            // just remove connection
            conn = null;
        }
        if ( connectionPoolActivedCount.get(key).get() > 0 ){
            connectionPoolActivedCount.get(key).decrementAndGet();
        }
        logDebug("<<<<< releaseConnection[type={},datasourceId={}] end  current idleAcount={},activedCount={} ",
        type, datasourceId, this.getIdleCount(type, datasourceId),
        this.getActivedCount(type, datasourceId));
        return true;

    }

    /**
     * 数据源连接池空闲连接数
     * @param   type
     * @param   datasourceId
     * @return  int
     */
    public int getIdleCount(String type, String datasourceId) {
        String key = type + "-" + datasourceId;
        return (connectionPool.get(key) == null) ? 0 : connectionPool.get(key).size();
    }
    /**
     * 数据源连接池使用中连接数
     * @param   type
     * @param   datasourceId
     * @return  int
     */
    public int getActivedCount(String type, String datasourceId) {
        String key = type + "-" + datasourceId;
        if (connectionPoolActivedCount.containsKey(key)){
            return connectionPoolActivedCount.get(key).get();
        } else{
            return 0;
        }
        
    }

    /**
     * 数据源连接池使用中和空闲中总连接数
     * @param   type
     * @param   datasourceId
     * @return  int
     */
    public int getTotalCount(String type, String datasourceId) {
        String key = type + "-" + datasourceId;
        int total = 0;
        if (connectionCountLockMap.get(key) == null ) {
            ReentrantLock lock = connectionCountLockMap.get(key);
            try {
                lock.lock();
                total = this.getIdleCount(type, datasourceId) + this.getActivedCount(type, datasourceId) ;
            } finally {
                lock.unlock();
            }
            
        } 
        return total;
        
        
    }
    /**
     * 提供jdbc更新操作
     * @param  type
     * @param  datasourceId
     * @param  sql
     * @return int sql执行影响记录数
     */
    public int executeUpdate(String type, String datasourceId, String sql) {
        // 受影响的行数
        int affectedLine = 0;
        Connection conn = null;
        try {
            conn = this.getConnection(type, datasourceId, 0);
            if (isValidConnection(conn)) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    affectedLine = preparedStatement.executeUpdate();
                }
            }

        } catch (Exception e) {
            LOGGER.error("><><> >> executeUpdate  exception: ", e);
        } finally {
            if (conn != null){
                this.releaseConnection(type, datasourceId, conn);
            }
        }
        return affectedLine;
    }

    /**
     * 获取结果集，并将结果放在List中
     * 
     * @param  sql SQL语句
     * @return List 结果集
     */
    public List<Map<String, Object>> excuteQueryListMap(String type, String datasourceId, String sql) {
        Connection conn = null;
        try {
            conn = this.getConnection(type, datasourceId, 0);
            //logDebug(">>>>> excuteQueryListMap[type={},datasourceId={}] current idleAcount={},activedCount={} ",type, datasourceId, this.getIdleCount(type, datasourceId),this.getActivedCount(type, datasourceId));
            if (isValidConnection(conn)) {
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    ResultSet rs = preparedStatement.executeQuery()) {

                    // 创建ResultSetMetaData对象
                    java.sql.ResultSetMetaData rsmd = rs.getMetaData();
                    // 结果集列数
                    int columnCount = rsmd.getColumnCount();

                    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                    while (rs.next()) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        for (int i = 1; i <= columnCount; i++) {
                            if ( DBTypeEnum.clickhouse.name().equals(type) ){
                                if (rs.getObject(i).getClass().equals(java.sql.Timestamp.class)) {
                                    map.put(rsmd.getColumnLabel(i), DATE_FORMATTER.format(rs.getTimestamp(i).toLocalDateTime()));
                                } else if (rs.getObject(i).getClass().equals(java.sql.Date.class)){
                                    map.put(rsmd.getColumnLabel(i), DATE_FORMATTER.format(rs.getDate(i).toLocalDate()));
                                } else {
                                    map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                                }
                                
                            } else {
                                map.put(rsmd.getColumnLabel(i), rs.getObject(i));
                            }
                            
                        }
                        list.add(map);
                    }
                    return list;
                }
            }
            return null;

        } catch (Exception e) {
            LOGGER.error("><><> excuteQueryListMap  exception: ", e);

            return null;
        } finally {
            logDebug(">>>>> excuteQueryListMap  releaseConnection datasourceKey = {}  connection={} ", type + "-" + datasourceId,conn);
            this.releaseConnection(type, datasourceId, conn);
        }

    }
    /**
     * 关闭连接池，不对外提供连接
     */
    @Override
    public void close() {
        this.isClosed = true;
    }
    /**
     * 打开连接池。提供连接
     */
    @Override
    public void open() {
        this.isClosed = false;
    }
    /**
     * 关闭连接池及连接池中所有连接，释放资源
     */
    @Override
    public void shutdown() {
        this.close();
        try {
            checkValidTimer.cancel();
            rebuildConnectionExecutor.shutdown();
            Thread.sleep(3000);
        } catch (InterruptedException e1) {
            logDebug( ">>>>> MixedConnectionPool shutdown wait 3 seconds interrupted exception  "  );
        }
        logDebug( ">>>>> MixedConnectionPool shutdown wait 3 seconds for all idleConnection  "  );
        for (Entry<String, LinkedBlockingQueue<Connection>> dsqueue : connectionPool.entrySet()) {
            String dsKey = dsqueue.getKey();
            logDebug( ">>>>> MixedConnectionPool shutdown[{}] idleConnection count[{}]  ",dsKey,dsqueue.getValue().size()  );
            for (Connection conn : dsqueue.getValue()) {
                if (isValidConnection(conn)) {
                    try {
                        conn.close();
                        conn =null ;
                    } catch (Exception e) {
                        LOGGER.error("><><> MixedConnectionPool shutdown[" + dsKey + "] close connection  exception: ", e);
                    }
                }
            }
            logDebug( ">>>>> MixedConnectionPool shutdown [{}] datasource over ...",dsKey );
    
        }

    }

    public static void main(String[] args) throws Exception {
        final MixedConnectionPool pool = new MixedConnectionPool(4, 3000, "mysql", "test",
                "jdbc:mysql://39.98.38.34:3306/DataStat?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
                "devuser", "");
        final MixedConnectionPool pool2 = new MixedConnectionPool(4, 3000, "clickhouse", "hivetest",
        "jdbc:clickhouse://cc-uf6u8tg778442686so.ads.rds.aliyuncs.com:8123/hive",
        "appuser", "");
        pool2.addDataSource("clickhouse", "gmmtest",
        "jdbc:clickhouse://cc-uf6u8tg778442686so.ads.rds.aliyuncs.com:8123/gmm",
        "appuser", "");
        pool2.addDataSource("mysql", "gmmtest",
        "jdbc:mysql://39.98.38.34:3306/DataStat?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull",
        "devuser", "");

        ExecutorService threadPool = Executors.newFixedThreadPool(8);

        // for (int i = 0; i < 20; i++) {
        //     threadPool.execute(() -> {
        //         // Connection con = pool.getConnection("mysql", "test", 0);
        //         List<Map<String, Object>> retList = pool.excuteQueryListMap("mysql", "test",
        //                 "select count(*) as cnt from DataStat.tMonitorConfig");
        //         System.out.println(">>>>[" + Thread.currentThread().getName() + "] mysql retList=" + retList);
        //         try {
        //             Thread.sleep(1000);
        //         } catch (InterruptedException e) {
        //              e.printStackTrace();
        //         }
        //     });
        //     Thread.currentThread().sleep(5000);
        // }

        for (int i = 0; i < 3; i++) {
            threadPool.execute(() -> {
                // Connection con = pool.getConnection("mysql", "test", 0);
                List<Map<String, Object>> retList = pool2.excuteQueryListMap("clickhouse", "hivetest",
                        "SELECT id as `编号`,reward_name AS `奖品编号`,update_time AS `更新时间` FROM hive.`Activity_Reward` LIMIT 20;");
                System.out.println(">>>>[" + Thread.currentThread().getName() + "] clickhouse retList=" + (retList == null  ? 0 : retList.size()) );
                List<Map<String, Object>> retList2 = pool2.excuteQueryListMap("mysql", "gmmtest",
                "select count(*) as cnt from tMonitorConfig");
                System.out.println(">>>>[" + Thread.currentThread().getName() + "] mysql gmmtest retList=" + retList2);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                     e.printStackTrace();
                }
            });
            Thread.sleep(5000);
        }

        
        Thread.sleep(10000);
        threadPool.shutdown();
        pool.shutdown();
        pool2.shutdown();
        Thread.sleep(5000);

    }


}

/**
 * IDBConnectionPool
 */
interface IDBConnectionPool {

    public static enum DBTypeEnum {
        mysql("mysql"),
        clickhouse("clickhouse");

        private String name;
        DBTypeEnum(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }

    /**
     *
     * @param type
     * @param datasourceId
     * @param connStr
     * @param userName
     * @param pwd
     * @return
     */
    Connection buildConnection(String type, String datasourceId, String connStr, String userName, String pwd);

    /**
     *
     * @param type
     * @param datasourceId
     * @param timeout
     * @return
     */
    Connection getConnection(String type, String datasourceId, long timeout);

    /**
     *
     * @param type
     * @param datasourceId
     * @param connection
     * @return
     */
    boolean releaseConnection(String type, String datasourceId, Connection connection);

    /**
     *
     */
    void close();

    /**
     *
     */
    void open();

    /**
     *
     */
    void shutdown();

    
    
}