package com.gupao.vip.pattern.singlerton.lazy;

import com.gupao.vip.pattern.singlerton.threadLocal.ThreadLocalSingleton;

/**
 * Created by qingbowu on 2019/3/10.
 */
public class ExcutorThread implements Runnable {

    @Override
    public void run() {
        LazySingleton lazySingleton =LazySingleton.getInstance();
        

        System.out.println(Thread.currentThread().getName()+"-->"+lazySingleton.toString());

        //测试LazySingleton
//        LazySingleton lazySingleton =LazySingleton.getInstance();
//        System.out.println(Thread.currentThread().getName()+"-->"+lazySingleton.toString());

        //测试ThreadLocalSingleton
        ThreadLocalSingleton singleton = ThreadLocalSingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+"-->"+singleton);
        System.out.println(Thread.currentThread().getName()+"-->"+ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName()+"-->"+ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName()+"-->"+ThreadLocalSingleton.getInstance());
        System.out.println(Thread.currentThread().getName()+"-->"+ThreadLocalSingleton.getInstance());

    }
}
