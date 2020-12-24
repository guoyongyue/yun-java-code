package com.yun.lock;

/**
 * @author yun
 */
public class MyReentranceLock {

    private transient Thread exclusiveOwnerThread;

    public Thread getExclusiveOwnerThread() {
        return exclusiveOwnerThread;
    }

    public void setExclusiveOwnerThread(Thread exclusiveOwnerThread) {
        this.exclusiveOwnerThread = exclusiveOwnerThread;
    }

    public void lock() {
        if (getExclusiveOwnerThread() == null) {
            setExclusiveOwnerThread(Thread.currentThread());
        }
    }

    public void unlock() {
        setExclusiveOwnerThread(null);
    }

    private boolean isCurrentThread(Thread thread) {
        return getExclusiveOwnerThread() == thread;
    }


    public static void main(String[] args) {

    }


}
