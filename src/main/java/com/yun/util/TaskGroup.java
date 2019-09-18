package com.yun.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class TaskGroup {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(TaskGroup.class);


    public static void main(String[] args) {
        TaskGroup taskGroup = getInstance();

        taskGroup.async(// 任务1
                () -> {
                    logger.info("task 1 begin");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread sleep fail");
                    }

                    System.out.println("task 1 end");
                },
                // 任务2
                ()->{
                    System.out.println("task 2 begin");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        System.out.println("Thread sleep fail");
                    }

                    System.out.println("task 2 end");
                }
        );
    }

    private static ThreadPoolExecutor threadPoolExecutor = null;

    public static class SingletonInner{
           private static TaskGroup taskGroup = new TaskGroup(100);
    }

    /**
     * 使用内部类的方式创建TaskGroup
     */
    public static TaskGroup getInstance(){
        return SingletonInner.taskGroup;
    }

    private TaskGroup(int maxPoolSize){
        /**
         * 核心线程池大小
         * 1.cpu密集型：
         *  CPU密集的意思是该任务需要大量的运算，而没有阻塞，CPU一直全速运行。
         *  CPU密集任务只有在真正的多核CPU才可能得到加速（通过多线程）。
         *  /而在单核CPU上，无论你开几个模拟的多线程该任务都不可能得到加速，因为CPU总的运算能力就那些。（不过现在应该没有单核的CPU了吧）/
         *  CPU密集型的任务配置尽可能少的线程数量：
         *  一般公式：CPU核数+1个线程的线程池。
         *
         * 2.IO密集型：（分两种）：
         *  1.由于IO密集型任务的线程并不是一直在执行任务，则应配置尽可能多的线程，如CPU核数*2
         *  2.IO密集型，即任务需要大量的IO，即大量的阻塞。在单线程上运行IO密集型的任务会导致浪费大量的CPU运算能力浪费在等待。所以在IO密集型任务中使用多线程可以大大的加速程序运行。故需要·多配置线程数：
         *  参考公式：CPU核数/（1-阻塞系数 ） 阻塞系数在（0.8-0.9）之间
         *  比如8核CPU：8/（1-0.9） = 80个线程数
         */
        int corePoolSize = 2;

        /**
         * 最大线程池大小
         */
        int maximumPoolSize = maxPoolSize;

        /**
         * 线程最大空闲时间
         */
        long keepAliveTime = 60;

        /**
         * 时间单位
         */
        java.util.concurrent.TimeUnit unit = java.util.concurrent.TimeUnit.SECONDS;

        /**
         * 线程等待队列
         */
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(100);

        /**
         * 线程创建工厂
         * ThreadFactory threadFactory = Executors.defaultThreadFactory();
         *  return new Executors.DefaultThreadFactory();返回用于创建新线程的默认线程工厂
         *  DefaultThreadFactory的构造方法主要做了 声明安全管理器 得到线程组 生成线程名前缀
         * ThreadFactory threadFactory = ThreadFactory.privilegedThreadFactory();返回用于创建新线程的线程工厂，这些新线程与当前线程具有相同的权限
         *  return new Executors.PrivilegedThreadFactory(); 主要添加了访问权限校验
         */
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        /**
         * 拒绝策略 核心代码void rejectedExecution(Runnable var1, ThreadPoolExecutor var2);
         * private static final RejectedExecutionHandler defaultHandler = new ThreadPoolExecutor.AbortPolicy();丢弃任务并抛出RejectedExecutionException异常。
         * private static final RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
         * private static final RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();也是丢弃任务，但是不抛出异常。
         * private static final RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();由调用线程处理该任务
         */
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    /**
     * 同时执行多个任务，并在任务全部执行完成后才返回
     * @param aoRunnables 同时执行的任务
     */
    final public void sync(final Runnable...aoRunnables) {
        if ( aoRunnables == null ) {
            return;
        }
        final int iLen = aoRunnables.length;
        if ( iLen == 0 ) {
            return;
        }
        // 同步计数对象
        int iCount = iLen - 1; // 我们将第一个任务放在调用线程执行即可
        final CountDownLatch oCountDown = new CountDownLatch(iCount);
        // 开始逐个添加异步执行的任务
        for ( ;; ) {
            if ( iCount == 0 ) {
                // 第一个任务直接执行即可
                try {
                    if ( aoRunnables[0] != null ) aoRunnables[0].run();
                } catch (Throwable t) {
                    System.out.println("sync, run first task error:" + t.toString());
                }

                // 等待其他任务全部结束
                try {
                    oCountDown.await();
                } catch (InterruptedException e) {
                }
                break;
            }
            // 不是最后一个任务添加到线程池运行
            final Task oTask = new Task(aoRunnables[iCount--], oCountDown);
            try {
                threadPoolExecutor.execute(oTask);
            } catch (Exception e) {
                System.out.println("sync error:{}" + e.toString());
                throw e;
            }
        }
    }

    /**
     * 异步执行多个任务，并返回一个同步计数对象，如果任务不能被异步执行将抛出异常
     * @param aoRunnables 多个需要异步执行的任务
     * @return 返回同步计数对象，没有传递任务则返回null
     * @throws RejectedExecutionException 有任务不能被异步执行时将抛出异常
     */
    final public CountDownLatch async(final Runnable...aoRunnables)
            throws RejectedExecutionException {
        if ( aoRunnables == null ) {
            return null;
        }
        final int iLen = aoRunnables.length;
        if ( iLen == 0 ) {
            return null;
        }
        // 同步计数对象
        CountDownLatch oCountDown = new CountDownLatch(iLen);
        // 开始逐个添加异步执行的任务到线程池运行
        for ( final Runnable oRunnable : aoRunnables ) {
            final Task oTask = new Task(oRunnable, oCountDown);
            try {
                threadPoolExecutor.execute(oTask);
            } catch (Exception e) {
                System.out.println("async error:{}" + e.toString());
                throw e;
            }
        }
        return oCountDown;
    }

    public void execute(Runnable command) {
        if (command == null) {
            throw new NullPointerException();
        } else {
            int c = this.ctl.get();
            //1,判断工作线程数<核心线程数
            if (workerCountOf(c) < this.corePoolSize) {
                if (this.addWorker(command, true)) {
                    return;
                }
                c = this.ctl.get();
            }
            //2,运行态 并将runnable加入队列
            if (isRunning(c) && this.workQueue.offer(command)) {
                int recheck = this.ctl.get();
                if (!isRunning(recheck) && this.remove(command)) {
                    //拒绝 runnable
                    this.reject(command);
                } else if (workerCountOf(recheck) == 0) {
                    this.addWorker((Runnable)null, false);
                }//3,使用尝试使用最大线程运行
            } else if (!this.addWorker(command, false)) {
                this.reject(command);
            }
        }
    }

    private boolean addWorker(Runnable firstTask, boolean core) {
        //所有有效线程的数量
        int c = this.ctl.get();
        // 0<=c<=536870912
        label253:
        while(!runStateAtLeast(c, 0) || !runStateAtLeast(c, 536870912) && firstTask == null && !this.workQueue.isEmpty()) {
            //工作线程数量小于核心线程数量？最大线程数量
            while(workerCountOf(c) < ((core ? this.corePoolSize : this.maximumPoolSize) & 536870911)) {
                //自旋CAS
                if (this.compareAndIncrementWorkerCount(c)) {
                    // 线程启动标志位
                    boolean workerStarted = false;
                    // 线程是否加入workers 标志位
                    boolean workerAdded = false;
                    ThreadPoolExecutor.Worker w = null;
                    try {
                        //Worker extends AbstractQueuedSynchronizer implements Runnable 创建worker
                        w = new ThreadPoolExecutor.Worker(firstTask);
                        Thread t = w.thread;
                        if (t != null) {
                            //this.mainLock = new ReentrantLock();构造方式时赋值
                            ReentrantLock mainLock = this.mainLock;
                            //非公平锁
                            mainLock.lock();
                            try {
                                int c = this.ctl.get();
                                // 获取到锁以后仍需检查ctl，可能在上一个获取到锁处理的线程可能会改变runState
                                // 如 ThreadFactory 创建失败 或线程池被 shut down等
                                if (isRunning(c) || runStateLessThan(c, 536870912) && firstTask == null) {
                                    if (t.isAlive()) {
                                        throw new IllegalThreadStateException();
                                    }
                                    //添加到workers集合 正在工作worker集合
                                    this.workers.add(w);
                                    int s = this.workers.size();
                                    if (s > this.largestPoolSize) {
                                        this.largestPoolSize = s;
                                    }
                                    workerAdded = true;
                                }
                            } finally {
                                mainLock.unlock();
                            }

                            if (workerAdded) {
                                // 启动线程 封装的太重了 runWorker(ThreadPoolExecutor.Worker w)
                                //在这个方法里会有this.workers.remove(w);调用
                                t.start();
                                workerStarted = true;
                            }
                        }
                    } finally {
                        // 失败操作
                        if (!workerStarted) {
                            this.addWorkerFailed(w);
                        }
                    }
                    return workerStarted;
                }
                c = this.ctl.get();
                if (runStateAtLeast(c, 0)) {
                    continue label253;
                }
            }
            return false;
        }
        return false;
    }

    final void runWorker(ThreadPoolExecutor.Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock();
        boolean completedAbruptly = true;

        try {
            while(task != null || (task = this.getTask()) != null) {
                w.lock();
                if ((runStateAtLeast(this.ctl.get(), 536870912) || Thread.interrupted() && runStateAtLeast(this.ctl.get(), 536870912)) && !wt.isInterrupted()) {
                    wt.interrupt();
                }

                try {
                    this.beforeExecute(wt, task);

                    try {
                        task.run();
                        this.afterExecute(task, (Throwable)null);
                    } catch (Throwable var14) {
                        this.afterExecute(task, var14);
                        throw var14;
                    }
                } finally {
                    task = null;
                    ++w.completedTasks;
                    w.unlock();
                }
            }

            completedAbruptly = false;
        } finally {
            this.processWorkerExit(w, completedAbruptly);
        }

    }

    private final class Worker extends AbstractQueuedSynchronizer implements Runnable {
        private static final long serialVersionUID = 6138294804551838833L;
        /** 每个worker有自己的内部线程，ThreadFactory创建失败时是null */
        final Thread thread;
        /** 初始化任务 */
        Runnable firstTask;
        /** 每个worker的完成任务数 */
        volatile long completedTasks;
        Worker(Runnable firstTask) {
            // 禁止线程在启动前被打断
            this.setState(-1);
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);
        }
        public void run() {
            this.runWorker(this);
        }
        // state = 0 代表未锁；state = 1 代表已锁
        protected boolean isHeldExclusively() {
            return this.getState() != 0;
        }
        protected boolean tryAcquire(int unused) {
            if (this.compareAndSetState(0, 1)) {
                this.setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else {
                return false;
            }
        }
        protected boolean tryRelease(int unused) {
            this.setExclusiveOwnerThread((Thread)null);
            this.setState(0);
            return true;
        }
        public void lock() {
            this.acquire(1);
        }
        public boolean tryLock() {
            return this.tryAcquire(1);
        }
        public void unlock() {
            this.release(1);
        }
        public boolean isLocked() {
            return this.isHeldExclusively();
        }
        // interrupt已启动线程
        void interruptIfStarted() {
            Thread t;
            if (this.getState() >= 0 && (t = this.thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException var3) {
                }
            }
        }
    }

    final void runWorker(ThreadPoolExecutor.Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        // 允许被 interrupt
        w.unlock();
        boolean completedAbruptly = true;
        try {
            // loop 直至 task = null （线程池关闭、超时等）
            // 注意这里的getTask()方法，我们配置的阻塞队列会在这里起作用
            while(task != null || (task = this.getTask()) != null) {
                w.lock();
                // 如果线程池停止，确保线程中断; 如果没有，确保线程不中断。这需要在第二种情况下进行重新获取ctl，以便在清除中断时处理shutdownNow竞争
                if ((runStateAtLeast(this.ctl.get(), 536870912) || Thread.interrupted() && runStateAtLeast(this.ctl.get(), 536870912)) && !wt.isInterrupted()) {
                    wt.interrupt();
                }
                try {
                    // 前置切点
                    this.beforeExecute(wt, task);

                    try {
                        task.run();
                        // 后置切点
                        this.afterExecute(task, (Throwable)null);
                    } catch (Throwable var14) {
                        this.afterExecute(task, var14);
                        throw var14;
                    }
                } finally {
                    task = null;
                    ++w.completedTasks;
                    w.unlock();
                }
            }

            completedAbruptly = false;
        } finally {
            // 线程退出工作
            this.processWorkerExit(w, completedAbruptly);
        }

    }

    //runWorker的主要任务就是一直loop循环
    private Runnable getTask() {
        // 上一次 poll() 是否超时
        boolean timedOut = false;
        while(true) {
            int c = this.ctl.get();
            // 是否继续处理任务 可以参见上一篇的状态控制
            if (runStateAtLeast(c, 0) && (runStateAtLeast(c, 536870912) || this.workQueue.isEmpty())) {
                this.decrementWorkerCount();
                return null;
            }

            int wc = workerCountOf(c);
            // 是否允许超时
            boolean timed = this.allowCoreThreadTimeOut || wc > this.corePoolSize;
            if (wc <= this.maximumPoolSize && (!timed || !timedOut) || wc <= 1 && !this.workQueue.isEmpty()) {
                try {
                    Runnable r = timed ? (Runnable)this.workQueue.poll(this.keepAliveTime, TimeUnit.NANOSECONDS) : (Runnable)this.workQueue.take();
                    if (r != null) {
                        return r;
                    }

                    timedOut = true;
                } catch (InterruptedException var6) {
                    timedOut = false;
                }
            } else if (this.compareAndDecrementWorkerCount(c)) {
                return null;
            }
        }
    }

    /**
     * 内部使用的任务类，包装一个等待执行的任务
     */
    private static final class Task implements Runnable {
        private Runnable moRunnable;
        private CountDownLatch moCountDown;
        /**
         * 构造
         * @param aoRunnable 真正需要执行的Runnable
         * @param aoCountDown 需要同步的计数对象
         */
        public Task(final Runnable aoRunnable, final CountDownLatch aoCountDown) {
            assert aoCountDown != null;
            moRunnable = aoRunnable;
            moCountDown = aoCountDown;
        }

        @Override
        public void run() {
            try {
                if ( moRunnable != null ) moRunnable.run();
            } catch (Throwable t) {
                System.out.println("Task.run error:"+ t.toString());
            }finally {
                moCountDown.countDown();
            }
        }
    }




}
