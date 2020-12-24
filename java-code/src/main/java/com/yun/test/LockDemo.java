package com.yun.test;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:J.U.C保证线程安全
 * @Author:BigRedCaps
 */
public class LockDemo {
    static ReentrantLock lock = new ReentrantLock();
    public static int count = 0;

    public static void incr() {
        try {
            // 加锁
            lock.lock();
            Thread.sleep(1);
            count++; //count++ (只会由一个线程来执行)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();

        }

    }

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(1);
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextInt(100));
            new Thread(LockDemo::incr).start();
        }
        Thread.sleep(4000);
        System.out.println(count);
    }
}