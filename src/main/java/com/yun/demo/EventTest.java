package com.yun.demo;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.*;

// main 方法
public class EventTest {


    public static void main(String[] args) {

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6,6,60,TimeUnit.SECONDS,workQueue);
        for (int i = 0; i < 10000; i++) {
            threadPoolExecutor.execute(new MyRunnable(i));
        }

        // 初始化发布者实体类
//        PublishService publishService = new PublishService();
        // 发送消息
//        publishService.sendMessage();
    }

    static class MyRunnable implements Runnable{
        int i=0;
        public MyRunnable(int i){
            this.i=i;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" run i:" + i);
        }
    }
}