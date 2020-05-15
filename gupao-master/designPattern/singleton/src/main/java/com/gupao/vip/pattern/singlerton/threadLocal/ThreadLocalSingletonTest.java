package com.gupao.vip.pattern.singlerton.threadLocal;

import com.gupao.vip.pattern.singlerton.lazy.ExcutorThread;

/**
 * Created by qingbowu on 2019/3/11.
 */
public class ThreadLocalSingletonTest {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new ExcutorThread(),"aaa");
        Thread t2 = new Thread(new ExcutorThread(),"bbb");

        t1.start();
        t2.start();

        System.out.println("end");
    }
}
