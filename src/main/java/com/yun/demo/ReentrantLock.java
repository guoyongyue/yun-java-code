/*
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yun.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//import jdk.internal.vm.annotation.ReservedStackAccess;

public class ReentrantLock implements Lock, Serializable {
    private static final long serialVersionUID = 7373984872572414699L;
    private final ReentrantLock.Sync sync;

    //001 构建ReentrantLock
    public ReentrantLock(boolean fair) {
        this.sync = (ReentrantLock.Sync)(fair ? new ReentrantLock.FairSync() : new ReentrantLock.NonfairSync());
    }

    //002 NonfairSync
    static final class NonfairSync extends ReentrantLock.Sync {
        private static final long serialVersionUID = 7316153563782823691L;

        NonfairSync() {
        }

        //004 钩子方法
        protected final boolean tryAcquire(int acquires) {
            return this.nonfairTryAcquire(acquires);
        }
    }

    //003 以cas方式尝试将AQS中的state从0更新为1
    // 会调用　
    // this.tryAcquire(arg) 当前线程尝试获取锁,若获取成功返回true,否则false
    // this.acquireQueued(this.addWaiter(AbstractQueuedSynchronizer.Node.EXCLUSIVE), arg)只有当前线程获取锁失败才会执行者这部分代码
    // selfInterrupt()
    public void lock() {
        this.sync.acquire(1);
    }

    //0010 解锁
    // 会调用　
    // release()
    // this.tryRelease(arg)
    // this.unparkSuccessor(h) //唤醒同步队列中被阻塞的线程
    public void unlock() {
        this.sync.release(1);
    }

    abstract static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -5179523762034025860L;
        Sync() {
        }

        //获得
        final boolean nonfairTryAcquire(int acquires) {
            // 获取当前线程实例
            Thread current = Thread.currentThread();
            //AQS类中private volatile int state; 0
            //获取state变量的值,即当前锁被重入的次数
            int c = this.getState();
            if (c == 0) {//state为0,说明当前锁未被任何线程持有
                if (this.compareAndSetState(0, acquires)) { //以cas方式获取锁
                    this.setExclusiveOwnerThread(current);//将当前线程标记为持有锁的线程
                    return true;//获取锁成功,非重入
                }
            } else if (current == this.getExclusiveOwnerThread()) {//当前线程就是持有锁的线程,说明该锁被重入了
                int nextc = c + acquires;//计算state变量要更新的值
                if (nextc < 0) {
                    throw new Error("Maximum lock count exceeded");
                }

                this.setState(nextc);//非同步方式更新state值
                return true;//获取锁成功,重入
            }
            return false;//走到这里说明尝试获取锁失败
        }

        //释放
        //由于执行该方法的线程必然持有锁,故该方法不需要任何同步操作。
        protected final boolean tryRelease(int releases) {
            int c = this.getState() - releases;//计算待更新的state值
            if (Thread.currentThread() != this.getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            } else {
                boolean free = false;
                if (c == 0) {//待更新的state值为0,说明持有锁的线程未重入,一旦释放锁其他线程将能获取
                    free = true;
                    this.setExclusiveOwnerThread((Thread)null);////清除锁的持有线程标记
                }

                this.setState(c);//更新state值
                return free;
            }
        }

        protected final boolean isHeldExclusively() {
            return this.getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject(this);
        }

        final Thread getOwner() {
            return this.getState() == 0 ? null : this.getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return this.isHeldExclusively() ? this.getState() : 0;
        }

        final boolean isLocked() {
            return this.getState() != 0;
        }

        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            this.setState(0);
        }
    }



    public void lockInterruptibly() throws InterruptedException {
        this.sync.acquireInterruptibly(1);
    }

    public boolean tryLock() {
        return this.sync.nonfairTryAcquire(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return this.sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }



    public Condition newCondition() {
        return this.sync.newCondition();
    }

    public int getHoldCount() {
        return this.sync.getHoldCount();
    }

    public boolean isHeldByCurrentThread() {
        return this.sync.isHeldExclusively();
    }

    public boolean isLocked() {
        return this.sync.isLocked();
    }

    public final boolean isFair() {
        return this.sync instanceof ReentrantLock.FairSync;
    }

    protected Thread getOwner() {
        return this.sync.getOwner();
    }

    public final boolean hasQueuedThreads() {
        return this.sync.hasQueuedThreads();
    }

    public final boolean hasQueuedThread(Thread thread) {
        return this.sync.isQueued(thread);
    }

    public final int getQueueLength() {
        return this.sync.getQueueLength();
    }

    protected Collection<Thread> getQueuedThreads() {
        return this.sync.getQueuedThreads();
    }

    public boolean hasWaiters(Condition condition) {
        if (condition == null) {
            throw new NullPointerException();
        } else if (!(condition instanceof ConditionObject)) {
            throw new IllegalArgumentException("not owner");
        } else {
            return this.sync.hasWaiters((ConditionObject)condition);
        }
    }

    public int getWaitQueueLength(Condition condition) {
        if (condition == null) {
            throw new NullPointerException();
        } else if (!(condition instanceof ConditionObject)) {
            throw new IllegalArgumentException("not owner");
        } else {
            return this.sync.getWaitQueueLength((ConditionObject)condition);
        }
    }

    protected Collection<Thread> getWaitingThreads(Condition condition) {
        if (condition == null) {
            throw new NullPointerException();
        } else if (!(condition instanceof ConditionObject)) {
            throw new IllegalArgumentException("not owner");
        } else {
            return this.sync.getWaitingThreads((ConditionObject)condition);
        }
    }

    public String toString() {
        Thread o = this.sync.getOwner();
        return super.toString() + (o == null ? "[Unlocked]" : "[Locked by thread " + o.getName() + "]");
    }

    static final class FairSync extends ReentrantLock.Sync {
        private static final long serialVersionUID = -3000897897090466540L;

        FairSync() {
        }

        protected final boolean tryAcquire(int acquires) {
            Thread current = Thread.currentThread();
            int c = this.getState();
            if (c == 0) {
                if (!this.hasQueuedPredecessors() && this.compareAndSetState(0, acquires)) {
                    this.setExclusiveOwnerThread(current);
                    return true;
                }
            } else if (current == this.getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) {
                    throw new Error("Maximum lock count exceeded");
                }

                this.setState(nextc);
                return true;
            }

            return false;
        }
    }



}
*/
