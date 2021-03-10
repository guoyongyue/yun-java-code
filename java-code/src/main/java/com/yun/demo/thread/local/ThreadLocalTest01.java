package com.yun.demo.thread.local;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ThreadLocalTest01 {
    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        Random random = new Random();
        IntStream.range(0,5).forEach(a-> new Thread(()->{
            local.set(a+" "+ random.nextInt(10));
            System.out.println("线程和local的值分别是 "+ local.get());
            try {
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start());
    }
}
