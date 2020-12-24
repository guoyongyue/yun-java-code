package com.yun.demo.thread.pool;

import java.util.UUID;
/**
 * @author yun
 */
public abstract class AbstractMyTask implements Runnable {

    String uuid = UUID.randomUUID().toString().replaceAll("-", "");

    /**
     * 要执行的代码
     */
    abstract public void task();

    @Override
    public void run() {
        begin();
        task();
        end();
    }

    private void begin() {
        System.out.println(this.getClass().getName()+ ":begin run, thread name:" + Thread.currentThread().getName() +
                ", uuid:" + uuid  + ", ThreadPoolExecutor size:" +
                ThreadPoolExecutorDemo.THREAD_POOL_EXECUTOR.getQueue().size());
    }

    public void print(String value) {
        System.out.println(this.getClass().getName() + ":running, thread name:"+
                Thread.currentThread().getName() + ", uuid:"  + uuid + ", print:" + value);
    }

    private void end() {
        System.out.println(this.getClass().getName()+ ":end, run thread name:" + Thread.currentThread().getName() +
                ", uuid:" + uuid  + ", ThreadPoolExecutor size:" +
                ThreadPoolExecutorDemo.THREAD_POOL_EXECUTOR.getQueue().size());
    }
}

