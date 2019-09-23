package com.yun.util;

import java.util.concurrent.locks.ReentrantLock;

public class Test {
    public static ReentrantLock nonfairSync = new ReentrantLock();//非公平（性能会更好）
    public static void main(String[] args) {
//    public static ReentrantLock fairSync = new ReentrantLock(false);//公平（实际测试差别不大）
    }

    public void lock() {
        nonfairSync.lock();
    }
}
