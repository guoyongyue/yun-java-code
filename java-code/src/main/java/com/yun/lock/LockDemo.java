package com.yun.lock;

import sun.misc.Unsafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:J.U.C保证线程安全
 * @Author:BigRedCaps
 */
public class LockDemo {
    static MyReentranceLock lock = new MyReentranceLock();
    public static int count=0;
    public static void incr(){
        try {
            // 加锁
            lock.lock();
            Thread.sleep(1);
            count++; //count++ (只会由一个线程来执行)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally
        {
            // 释放锁
            lock.unlock();
       }

    }
    public static void main(String[] args) throws InterruptedException {
       final Unsafe unsafe = Unsafe.getUnsafe();
        for (int i = 0; i < 10; i++) {
            new Thread(LockDemo::incr).start();
        }
        Thread.sleep(50000);
        System.out.println("result:"+count);
    }
}