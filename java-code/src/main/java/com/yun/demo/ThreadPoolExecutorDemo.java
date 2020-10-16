package com.yun.demo;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yun
 */
public class ThreadPoolExecutorDemo {

    private static int CORE_POOL_SIZE = 2;
    /**
     * 使用的时LinkedBlockingQueue 理论上能容纳所以的task 故不会创建新的线程CORE_POOL_SIZE和MAXI_MUM_POOL_SIZE应保持一致
     */
    private static int MAXI_MUM_POOL_SIZE = 2;
    private static long KEEP_ALIVE_TIME = 60 * 5;
    private static TimeUnit UNIT = TimeUnit.SECONDS;
    private static BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>();
    private static ThreadFactory THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger integer = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPool thread " + integer.getAndIncrement());
        }
    };

    public static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXI_MUM_POOL_SIZE, KEEP_ALIVE_TIME, UNIT, WORK_QUEUE, THREAD_FACTORY);

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            Runnable thread = new MyThread();
            THREAD_POOL_EXECUTOR.execute(thread);
        }
        ;
    }
}

