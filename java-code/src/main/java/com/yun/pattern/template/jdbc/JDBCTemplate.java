package com.yun.pattern.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JDBCTemplate {
    private DataSource dataSource;

    public JDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> executeQuery(String sql, RowMapper<Object> rowMapper, Object[] values) {
        try {
            //1、获取连接
            Connection connection = this.getConnection();
            //2、创建语句集
            PreparedStatement pstm = this.createPrepareStatement(connection, sql);
            //3、执行语句集
            ResultSet rs = this.executeQuery(pstm, values);
            //4、处理结果集
            List<?> list =this.parseResultSet(rs,rowMapper);
            //5、关闭结果集
            this.closeResultSet(rs);
            //6、关闭语句集
            this.closeStaement(pstm);
            //7、关闭连接
            this.closeConnection(connection);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected  void closeConnection(Connection connection) throws Exception {
        connection.close();
    }

    protected  void closeStaement(PreparedStatement pstm) throws Exception {
        pstm.close();
    }

    protected  void closeResultSet(ResultSet rs) throws Exception {
        rs.close();
    }


    protected List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<Object>();
        int rowNum = 1;
        while (rs.next()){
            result.add(rowMapper.mapRow(rs,rowNum++));
        }
        return result;
    }

    public ResultSet executeQuery(PreparedStatement pstm, Object[] values) throws SQLException {
        for (int i=1;i<values.length;i++){
            pstm.setObject(i,values[i]);
        }
        return pstm.executeQuery();
    }

    protected PreparedStatement createPrepareStatement(Connection connection, String sql) throws Exception {
        return connection.prepareStatement(sql);
    }

    protected Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }


}
