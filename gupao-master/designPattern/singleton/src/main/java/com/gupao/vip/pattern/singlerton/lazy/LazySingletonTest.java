package com.gupao.vip.pattern.singlerton.lazy;

/**
 * Created by qingbowu on 2019/3/10.
 */
public class LazySingletonTest {

    public static void main(String[] args) {
        Thread t1 = new Thread(new ExcutorThread(),"111");
        Thread t2 = new Thread(new ExcutorThread(),"222");

        t1.start();
        t2.start();

        System.out.println("end");
    }
}
