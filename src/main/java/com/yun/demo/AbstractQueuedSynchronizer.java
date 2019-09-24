//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yun.demo;

import java.io.Serializable;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;

public abstract class AbstractQueuedSynchronizer extends AbstractOwnableSynchronizer implements Serializable {
    private static final long serialVersionUID = 7373984972572414691L;
    //指向队列首元素的头指针
    private transient volatile AbstractQueuedSynchronizer.Node head;
    //指向队列尾元素的尾指针
    private transient volatile AbstractQueuedSynchronizer.Node tail;
    //当state为0表示该锁不被任何线程持有;
    // 当state为1表示线程恰好持有该锁1次(未重入);
    // 当state大于1则表示锁被线程重入state次。
    // 因为这是一个会被并发访问的量,为了防止出现可见性问题要用volatile进行修饰。
    private volatile int state;

    static final long SPIN_FOR_TIMEOUT_THRESHOLD = 1000L;
    private static final VarHandle STATE;
    private static final VarHandle HEAD;
    private static final VarHandle TAIL;

    // 首先尝试快速获取锁,以cas的方式将state的值更新为1,
    // 只有当state的原值为0时更新才能成功,
    // 因为state在ReentrantLock的语境下等同于锁被线程重入的次数,
    // 这意味着只有当前锁未被任何线程持有时该动作才会返回成功。
    // 若获取锁成功,则将当前线程标记为持有锁的线程,然后整个加锁流程就结束了。
    public final void acquire(int arg) {
        // 若获取锁失败,则执行acquireQueued方法
        if (!this.tryAcquire(arg) && this.acquireQueued(this.addWaiter(AbstractQueuedSynchronizer.Node.EXCLUSIVE), arg)) {
            selfInterrupt();
        }
    }

    //添加一个新节点
    private AbstractQueuedSynchronizer.Node addWaiter(AbstractQueuedSynchronizer.Node mode) {
        //创建一个新的节点
        AbstractQueuedSynchronizer.Node node = new AbstractQueuedSynchronizer.Node(mode);

        AbstractQueuedSynchronizer.Node oldTail;
        do {
            while (true) {
                //t指向当前队列的最后一个节点,队列为空则为null
                oldTail = this.tail;
                if (oldTail != null) {
                    //设置新节点的前一个节点为最后一个节点　PREV.set(this, p)　ｃａｓ
                    node.setPrevRelaxed(oldTail);
                    break;
                }
                //设置head节点为新节点　ｃａｓ
                this.initializeSyncQueue();
            }//判断节点是否添加成功　自旋
        } while (!this.compareAndSetTail(oldTail, node));//

        oldTail.next = node;
        return node;
    }

    //设置head节点
    private final void initializeSyncQueue() {
        AbstractQueuedSynchronizer.Node h;
        if (HEAD.compareAndSet(this, (Void) null, h = new AbstractQueuedSynchronizer.Node())) {
            this.tail = h;
        }

    }

    //判断节点是否添加成功
    private final boolean compareAndSetTail(AbstractQueuedSynchronizer.Node expect, AbstractQueuedSynchronizer.Node update) {
        return TAIL.compareAndSet(this, expect, update);
    }

    //第一个if分句中,当前线程首先会判断前驱结点是否是头结点,如果是则尝试获取锁,
    // 获取锁成功则会设置当前结点为头结点(更新头指针)。为什么必须前驱结点为头结点才尝试去获取锁？
    // 因为头结点表示当前正占有锁的线程,正常情况下该线程释放锁后会通知后面结点中阻塞的线程,
    // 阻塞线程被唤醒后去获取锁,这是我们希望看到的。然而还有一种情况,就是前驱结点取消了等待,
    // 此时当前线程也会被唤醒,这时候就不应该去获取锁,而是往前回溯一直找到一个没有取消等待的结点,
    // 然后将自身连接在它后面。一旦我们成功获取了锁并成功将自身设置为头结点,就会跳出for循环。
    // 否则就会执行第二个if分句:确保前驱结点的状态为SIGNAL,然后阻塞当前线程。
    final boolean acquireQueued(AbstractQueuedSynchronizer.Node node, int arg) {
        boolean interrupted = false;

        try {
            //死循环,正常情况下线程只有获得锁才能跳出循环
            while (true) {
                //获得当前线程所在结点的前驱结点
                AbstractQueuedSynchronizer.Node p = node.predecessor();
                if (p == this.head && this.tryAcquire(arg)) {
                    this.setHead(node);//将当前结点设置为队列头结点
                    p.next = null;
                    return interrupted;//正常情况下死循环唯一的出口
                }

                if (shouldParkAfterFailedAcquire(p, node)) { //判断是否要阻塞当前线程
                    interrupted |= this.parkAndCheckInterrupt(); //阻塞当前线程
                }
            }
        } catch (Throwable var5) {
            this.cancelAcquire(node);
            if (interrupted) {
                selfInterrupt();
            }

            throw var5;
        }
    }

    //判断是否要阻塞当前线程
    private static boolean shouldParkAfterFailedAcquire(AbstractQueuedSynchronizer.Node pred, AbstractQueuedSynchronizer.Node node) {
        int ws = pred.waitStatus;
        if (ws == -1) {
            return true;
        } else {
            if (ws > 0) {//状态为CANCELLED,
                do {
                    node.prev = pred = pred.prev;
                } while (pred.waitStatus > 0);

                pred.next = node;
            } else {//状态为初始化状态(ReentrentLock语境下)
                pred.compareAndSetWaitStatus(ws, -1);
            }

            return false;
        }
    }

    // 阻塞当前线程
    private final boolean parkAndCheckInterrupt() {
        LockSupport.park(this);
        return Thread.interrupted();
    }


    //释放
    public final boolean release(int arg) {
        if (this.tryRelease(arg)) {//释放锁(state-1),若释放后锁可被其他线程获取(state=0),返回true
            AbstractQueuedSynchronizer.Node h = this.head;
            //当前队列不为空且头结点状态不为初始化状态(0)
            // h!=null是为了防止队列为空,即没有任何线程处于等待队列中,那么也就不需要进行唤醒的操作
            // h.waitStatus != 0是为了防止队列中虽有线程,但该线程还未阻塞,由前面的分析知,
            // 线程在阻塞自己前必须设置前驱结点的状态为SIGNAL,否则它不会阻塞自己。
            if (h != null && h.waitStatus != 0) {
                this.unparkSuccessor(h);//唤醒同步队列中被阻塞的线程
            }

            return true;
        } else {
            return false;
        }
    }

    //唤醒线程
    //一般情况下只要唤醒后继结点的线程就行了,但是后继结点可能已经取消等待,所以从队列尾部往前回溯,
    // 找到离头结点最近的正常结点,并唤醒其线程。
    private void unparkSuccessor(AbstractQueuedSynchronizer.Node node) {
        int ws = node.waitStatus;
        if (ws < 0) {
            node.compareAndSetWaitStatus(ws, 0);
        }

        AbstractQueuedSynchronizer.Node s = node.next;
        if (s == null || s.waitStatus > 0) {
            s = null;

            for (AbstractQueuedSynchronizer.Node p = this.tail; p != node && p != null; p = p.prev) {
                if (p.waitStatus <= 0) {
                    s = p;
                }
            }
        }

        if (s != null) {
            LockSupport.unpark(s.thread);
        }

    }


    //钩子方法子类实现
    protected boolean tryAcquire(int arg) {
        throw new UnsupportedOperationException();
    }


    static final class Node {
        //共享模式
        static final AbstractQueuedSynchronizer.Node SHARED = new AbstractQueuedSynchronizer.Node();
        //独占模式
        static final AbstractQueuedSynchronizer.Node EXCLUSIVE = null;
        static final int CANCELLED = 1;  //当前线程被取消
        static final int SIGNAL = -1; //当前节点的后继节点要运行，也就是unpark
        static final int CONDITION = -2; //当前节点在condition队列中等待
        static final int PROPAGATE = -3; //后继的acquireShared能够得以执行，读写锁和信号量使用
        //对于重入锁而言,主要有3个值。0:初始化状态；-1(SIGNAL):
        // 当前结点表示的线程在释放锁后需要唤醒后续节点的线程;1(CANCELLED):在同步队列中等待的线程等待超时或者被中断,取消继续等待。
        volatile int waitStatus;
        //前驱节点
        volatile AbstractQueuedSynchronizer.Node prev;
        //后继节点
        volatile AbstractQueuedSynchronizer.Node next;
        //当前结点表示的线程,因为同步队列中的结点内部封装了之前竞争锁失败的线程,故而结点内部必然有一个对应线程实例的引用
        volatile Thread thread;

        //下一个等待者
        AbstractQueuedSynchronizer.Node nextWaiter;

        private static final VarHandle NEXT;
        private static final VarHandle PREV;
        private static final VarHandle THREAD;
        private static final VarHandle WAITSTATUS;

        final boolean isShared() {
            return this.nextWaiter == SHARED;
        }

        final AbstractQueuedSynchronizer.Node predecessor() {
            AbstractQueuedSynchronizer.Node p = this.prev;
            if (p == null) {
                throw new NullPointerException();
            } else {
                return p;
            }
        }

        Node() {
        }

        Node(AbstractQueuedSynchronizer.Node nextWaiter) {
            this.nextWaiter = nextWaiter;
            THREAD.set(this, Thread.currentThread());
        }

        Node(int waitStatus) {
            WAITSTATUS.set(this, waitStatus);
            THREAD.set(this, Thread.currentThread());
        }

        final boolean compareAndSetWaitStatus(int expect, int update) {
            return WAITSTATUS.compareAndSet(this, expect, update);
        }

        final boolean compareAndSetNext(AbstractQueuedSynchronizer.Node expect, AbstractQueuedSynchronizer.Node update) {
            return NEXT.compareAndSet(this, expect, update);
        }

        final void setPrevRelaxed(AbstractQueuedSynchronizer.Node p) {
            PREV.set(this, p);
        }

        static {
            try {
                Lookup l = MethodHandles.lookup();
                NEXT = l.findVarHandle(AbstractQueuedSynchronizer.Node.class, "next", AbstractQueuedSynchronizer.Node.class);
                PREV = l.findVarHandle(AbstractQueuedSynchronizer.Node.class, "prev", AbstractQueuedSynchronizer.Node.class);
                THREAD = l.findVarHandle(AbstractQueuedSynchronizer.Node.class, "thread", Thread.class);
                WAITSTATUS = l.findVarHandle(AbstractQueuedSynchronizer.Node.class, "waitStatus", Integer.TYPE);
            } catch (ReflectiveOperationException var1) {
                throw new ExceptionInInitializerError(var1);
            }
        }
    }

    protected AbstractQueuedSynchronizer() {
    }

    protected final int getState() {
        return this.state;
    }

    protected final void setState(int newState) {
        this.state = newState;
    }

    protected final boolean compareAndSetState(int expect, int update) {
        return STATE.compareAndSet(this, expect, update);
    }

    private AbstractQueuedSynchronizer.Node enq(AbstractQueuedSynchronizer.Node node) {
        while (true) {
            AbstractQueuedSynchronizer.Node oldTail = this.tail;
            if (oldTail != null) {
                node.setPrevRelaxed(oldTail);
                if (this.compareAndSetTail(oldTail, node)) {
                    oldTail.next = node;
                    return oldTail;
                }
            } else {
                this.initializeSyncQueue();
            }
        }
    }


    private void setHead(AbstractQueuedSynchronizer.Node node) {
        this.head = node;
        node.thread = null;
        node.prev = null;
    }


    private void doReleaseShared() {
        while (true) {
            AbstractQueuedSynchronizer.Node h = this.head;
            if (h != null && h != this.tail) {
                int ws = h.waitStatus;
                if (ws == -1) {
                    if (!h.compareAndSetWaitStatus(-1, 0)) {
                        continue;
                    }

                    this.unparkSuccessor(h);
                } else if (ws == 0 && !h.compareAndSetWaitStatus(0, -3)) {
                    continue;
                }
            }

            if (h == this.head) {
                return;
            }
        }
    }

    private void setHeadAndPropagate(AbstractQueuedSynchronizer.Node node, int propagate) {
        AbstractQueuedSynchronizer.Node h = this.head;
        this.setHead(node);
        if (propagate > 0 || h == null || h.waitStatus < 0 || (h = this.head) == null || h.waitStatus < 0) {
            AbstractQueuedSynchronizer.Node s = node.next;
            if (s == null || s.isShared()) {
                this.doReleaseShared();
            }
        }

    }

    private void cancelAcquire(AbstractQueuedSynchronizer.Node node) {
        if (node != null) {
            node.thread = null;

            AbstractQueuedSynchronizer.Node pred;
            for (pred = node.prev; pred.waitStatus > 0; node.prev = pred = pred.prev) {
            }

            AbstractQueuedSynchronizer.Node predNext = pred.next;
            node.waitStatus = 1;
            if (node == this.tail && this.compareAndSetTail(node, pred)) {
                pred.compareAndSetNext(predNext, (AbstractQueuedSynchronizer.Node) null);
            } else {
                int ws;
                if (pred != this.head && ((ws = pred.waitStatus) == -1 || ws <= 0 && pred.compareAndSetWaitStatus(ws, -1)) && pred.thread != null) {
                    AbstractQueuedSynchronizer.Node next = node.next;
                    if (next != null && next.waitStatus <= 0) {
                        pred.compareAndSetNext(predNext, next);
                    }
                } else {
                    this.unparkSuccessor(node);
                }

                node.next = node;
            }

        }
    }


    static void selfInterrupt() {
        Thread.currentThread().interrupt();
    }


    private void doAcquireInterruptibly(int arg) throws InterruptedException {
        AbstractQueuedSynchronizer.Node node = this.addWaiter(AbstractQueuedSynchronizer.Node.EXCLUSIVE);

        try {
            AbstractQueuedSynchronizer.Node p;
            do {
                p = node.predecessor();
                if (p == this.head && this.tryAcquire(arg)) {
                    this.setHead(node);
                    p.next = null;
                    return;
                }
            } while (!shouldParkAfterFailedAcquire(p, node) || !this.parkAndCheckInterrupt());

            throw new InterruptedException();
        } catch (Throwable var4) {
            this.cancelAcquire(node);
            throw var4;
        }
    }

    private boolean doAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (nanosTimeout <= 0L) {
            return false;
        } else {
            long deadline = System.nanoTime() + nanosTimeout;
            AbstractQueuedSynchronizer.Node node = this.addWaiter(AbstractQueuedSynchronizer.Node.EXCLUSIVE);

            try {
                do {
                    AbstractQueuedSynchronizer.Node p = node.predecessor();
                    if (p == this.head && this.tryAcquire(arg)) {
                        this.setHead(node);
                        p.next = null;
                        return true;
                    }

                    nanosTimeout = deadline - System.nanoTime();
                    if (nanosTimeout <= 0L) {
                        this.cancelAcquire(node);
                        return false;
                    }

                    if (shouldParkAfterFailedAcquire(p, node) && nanosTimeout > 1000L) {
                        LockSupport.parkNanos(this, nanosTimeout);
                    }
                } while (!Thread.interrupted());

                throw new InterruptedException();
            } catch (Throwable var8) {
                this.cancelAcquire(node);
                throw var8;
            }
        }
    }

    private void doAcquireShared(int arg) {
        AbstractQueuedSynchronizer.Node node = this.addWaiter(AbstractQueuedSynchronizer.Node.SHARED);
        boolean interrupted = false;

        try {
            while (true) {
                AbstractQueuedSynchronizer.Node p = node.predecessor();
                if (p == this.head) {
                    int r = this.tryAcquireShared(arg);
                    if (r >= 0) {
                        this.setHeadAndPropagate(node, r);
                        p.next = null;
                        return;
                    }
                }

                if (shouldParkAfterFailedAcquire(p, node)) {
                    interrupted |= this.parkAndCheckInterrupt();
                }
            }
        } catch (Throwable var9) {
            this.cancelAcquire(node);
            throw var9;
        } finally {
            if (interrupted) {
                selfInterrupt();
            }

        }
    }

    private void doAcquireSharedInterruptibly(int arg) throws InterruptedException {
        AbstractQueuedSynchronizer.Node node = this.addWaiter(AbstractQueuedSynchronizer.Node.SHARED);

        try {
            AbstractQueuedSynchronizer.Node p;
            do {
                p = node.predecessor();
                if (p == this.head) {
                    int r = this.tryAcquireShared(arg);
                    if (r >= 0) {
                        this.setHeadAndPropagate(node, r);
                        p.next = null;
                        return;
                    }
                }
            } while (!shouldParkAfterFailedAcquire(p, node) || !this.parkAndCheckInterrupt());

            throw new InterruptedException();
        } catch (Throwable var5) {
            this.cancelAcquire(node);
            throw var5;
        }
    }

    private boolean doAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (nanosTimeout <= 0L) {
            return false;
        } else {
            long deadline = System.nanoTime() + nanosTimeout;
            AbstractQueuedSynchronizer.Node node = this.addWaiter(AbstractQueuedSynchronizer.Node.SHARED);

            try {
                do {
                    AbstractQueuedSynchronizer.Node p = node.predecessor();
                    if (p == this.head) {
                        int r = this.tryAcquireShared(arg);
                        if (r >= 0) {
                            this.setHeadAndPropagate(node, r);
                            p.next = null;
                            return true;
                        }
                    }

                    nanosTimeout = deadline - System.nanoTime();
                    if (nanosTimeout <= 0L) {
                        this.cancelAcquire(node);
                        return false;
                    }

                    if (shouldParkAfterFailedAcquire(p, node) && nanosTimeout > 1000L) {
                        LockSupport.parkNanos(this, nanosTimeout);
                    }
                } while (!Thread.interrupted());

                throw new InterruptedException();
            } catch (Throwable var9) {
                this.cancelAcquire(node);
                throw var9;
            }
        }
    }


    protected boolean tryRelease(int arg) {
        throw new UnsupportedOperationException();
    }

    protected int tryAcquireShared(int arg) {
        throw new UnsupportedOperationException();
    }

    protected boolean tryReleaseShared(int arg) {
        throw new UnsupportedOperationException();
    }

    protected boolean isHeldExclusively() {
        throw new UnsupportedOperationException();
    }


    public final void acquireInterruptibly(int arg) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else {
            if (!this.tryAcquire(arg)) {
                this.doAcquireInterruptibly(arg);
            }

        }
    }

    public final boolean tryAcquireNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else {
            return this.tryAcquire(arg) || this.doAcquireNanos(arg, nanosTimeout);
        }
    }


    public final void acquireShared(int arg) {
        if (this.tryAcquireShared(arg) < 0) {
            this.doAcquireShared(arg);
        }

    }

    public final void acquireSharedInterruptibly(int arg) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else {
            if (this.tryAcquireShared(arg) < 0) {
                this.doAcquireSharedInterruptibly(arg);
            }

        }
    }

    public final boolean tryAcquireSharedNanos(int arg, long nanosTimeout) throws InterruptedException {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        } else {
            return this.tryAcquireShared(arg) >= 0 || this.doAcquireSharedNanos(arg, nanosTimeout);
        }
    }

    public final boolean releaseShared(int arg) {
        if (this.tryReleaseShared(arg)) {
            this.doReleaseShared();
            return true;
        } else {
            return false;
        }
    }

    public final boolean hasQueuedThreads() {
        AbstractQueuedSynchronizer.Node p = this.tail;

        for (AbstractQueuedSynchronizer.Node h = this.head; p != h && p != null; p = p.prev) {
            if (p.waitStatus <= 0) {
                return true;
            }
        }

        return false;
    }

    public final boolean hasContended() {
        return this.head != null;
    }

    public final Thread getFirstQueuedThread() {
        return this.head == this.tail ? null : this.fullGetFirstQueuedThread();
    }

    private Thread fullGetFirstQueuedThread() {
        AbstractQueuedSynchronizer.Node h;
        AbstractQueuedSynchronizer.Node s;
        Thread st;
        if (((h = this.head) == null || (s = h.next) == null || s.prev != this.head || (st = s.thread) == null) && ((h = this.head) == null || (s = h.next) == null || s.prev != this.head || (st = s.thread) == null)) {
            Thread firstThread = null;

            for (AbstractQueuedSynchronizer.Node p = this.tail; p != null && p != this.head; p = p.prev) {
                Thread t = p.thread;
                if (t != null) {
                    firstThread = t;
                }
            }

            return firstThread;
        } else {
            return st;
        }
    }

    public final boolean isQueued(Thread thread) {
        if (thread == null) {
            throw new NullPointerException();
        } else {
            for (AbstractQueuedSynchronizer.Node p = this.tail; p != null; p = p.prev) {
                if (p.thread == thread) {
                    return true;
                }
            }

            return false;
        }
    }

    final boolean apparentlyFirstQueuedIsExclusive() {
        AbstractQueuedSynchronizer.Node h;
        AbstractQueuedSynchronizer.Node s;
        return (h = this.head) != null && (s = h.next) != null && !s.isShared() && s.thread != null;
    }

    public final boolean hasQueuedPredecessors() {
        AbstractQueuedSynchronizer.Node h;
        if ((h = this.head) != null) {
            AbstractQueuedSynchronizer.Node s;
            if ((s = h.next) == null || s.waitStatus > 0) {
                s = null;

                for (AbstractQueuedSynchronizer.Node p = this.tail; p != h && p != null; p = p.prev) {
                    if (p.waitStatus <= 0) {
                        s = p;
                    }
                }
            }

            if (s != null && s.thread != Thread.currentThread()) {
                return true;
            }
        }

        return false;
    }

    public final int getQueueLength() {
        int n = 0;

        for (AbstractQueuedSynchronizer.Node p = this.tail; p != null; p = p.prev) {
            if (p.thread != null) {
                ++n;
            }
        }

        return n;
    }

    public final Collection<Thread> getQueuedThreads() {
        ArrayList<Thread> list = new ArrayList();

        for (AbstractQueuedSynchronizer.Node p = this.tail; p != null; p = p.prev) {
            Thread t = p.thread;
            if (t != null) {
                list.add(t);
            }
        }

        return list;
    }

    public final Collection<Thread> getExclusiveQueuedThreads() {
        ArrayList<Thread> list = new ArrayList();

        for (AbstractQueuedSynchronizer.Node p = this.tail; p != null; p = p.prev) {
            if (!p.isShared()) {
                Thread t = p.thread;
                if (t != null) {
                    list.add(t);
                }
            }
        }

        return list;
    }

    public final Collection<Thread> getSharedQueuedThreads() {
        ArrayList<Thread> list = new ArrayList();

        for (AbstractQueuedSynchronizer.Node p = this.tail; p != null; p = p.prev) {
            if (p.isShared()) {
                Thread t = p.thread;
                if (t != null) {
                    list.add(t);
                }
            }
        }

        return list;
    }

    public String toString() {
        return super.toString() + "[State = " + this.getState() + ", " + (this.hasQueuedThreads() ? "non" : "") + "empty queue]";
    }

    final boolean isOnSyncQueue(AbstractQueuedSynchronizer.Node node) {
        if (node.waitStatus != -2 && node.prev != null) {
            return node.next != null ? true : this.findNodeFromTail(node);
        } else {
            return false;
        }
    }

    private boolean findNodeFromTail(AbstractQueuedSynchronizer.Node node) {
        for (AbstractQueuedSynchronizer.Node p = this.tail; p != node; p = p.prev) {
            if (p == null) {
                return false;
            }
        }

        return true;
    }

    final boolean transferForSignal(AbstractQueuedSynchronizer.Node node) {
        if (!node.compareAndSetWaitStatus(-2, 0)) {
            return false;
        } else {
            AbstractQueuedSynchronizer.Node p = this.enq(node);
            int ws = p.waitStatus;
            if (ws > 0 || !p.compareAndSetWaitStatus(ws, -1)) {
                LockSupport.unpark(node.thread);
            }

            return true;
        }
    }

    final boolean transferAfterCancelledWait(AbstractQueuedSynchronizer.Node node) {
        if (node.compareAndSetWaitStatus(-2, 0)) {
            this.enq(node);
            return true;
        } else {
            while (!this.isOnSyncQueue(node)) {
                Thread.yield();
            }

            return false;
        }
    }

    final int fullyRelease(AbstractQueuedSynchronizer.Node node) {
        try {
            int savedState = this.getState();
            if (this.release(savedState)) {
                return savedState;
            } else {
                throw new IllegalMonitorStateException();
            }
        } catch (Throwable var3) {
            node.waitStatus = 1;
            throw var3;
        }
    }

    public final boolean owns(AbstractQueuedSynchronizer.ConditionObject condition) {
        return condition.isOwnedBy(this);
    }

    public final boolean hasWaiters(AbstractQueuedSynchronizer.ConditionObject condition) {
        if (!this.owns(condition)) {
            throw new IllegalArgumentException("Not owner");
        } else {
            return condition.hasWaiters();
        }
    }

    public final int getWaitQueueLength(AbstractQueuedSynchronizer.ConditionObject condition) {
        if (!this.owns(condition)) {
            throw new IllegalArgumentException("Not owner");
        } else {
            return condition.getWaitQueueLength();
        }
    }

    public final Collection<Thread> getWaitingThreads(AbstractQueuedSynchronizer.ConditionObject condition) {
        if (!this.owns(condition)) {
            throw new IllegalArgumentException("Not owner");
        } else {
            return condition.getWaitingThreads();
        }
    }


    static {
        try {
            Lookup l = MethodHandles.lookup();
            STATE = l.findVarHandle(AbstractQueuedSynchronizer.class, "state", Integer.TYPE);
            HEAD = l.findVarHandle(AbstractQueuedSynchronizer.class, "head", AbstractQueuedSynchronizer.Node.class);
            TAIL = l.findVarHandle(AbstractQueuedSynchronizer.class, "tail", AbstractQueuedSynchronizer.Node.class);
        } catch (ReflectiveOperationException var1) {
            throw new ExceptionInInitializerError(var1);
        }

        Class var2 = LockSupport.class;
    }

    /**
     * ConditionObject主要是为并发编程中的同步提供了等待通知的实现方式，
     * 可以在不满足某个条件的时候挂起线程等待。直到满足某个条件的时候在唤醒线程。
     */
    public class ConditionObject implements Condition, Serializable {
        private static final long serialVersionUID = 1173984872572414699L;
        private transient AbstractQueuedSynchronizer.Node firstWaiter;
        private transient AbstractQueuedSynchronizer.Node lastWaiter;
        private static final int REINTERRUPT = 1;
        private static final int THROW_IE = -1;

        public ConditionObject() {
        }

        private AbstractQueuedSynchronizer.Node addConditionWaiter() {
            if (!AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            } else {
                AbstractQueuedSynchronizer.Node t = this.lastWaiter;
                if (t != null && t.waitStatus != -2) {
                    this.unlinkCancelledWaiters();
                    t = this.lastWaiter;
                }

                AbstractQueuedSynchronizer.Node node = new AbstractQueuedSynchronizer.Node(-2);
                if (t == null) {
                    this.firstWaiter = node;
                } else {
                    t.nextWaiter = node;
                }

                this.lastWaiter = node;
                return node;
            }
        }

        private void doSignal(AbstractQueuedSynchronizer.Node first) {
            do {
                if ((this.firstWaiter = first.nextWaiter) == null) {
                    this.lastWaiter = null;
                }

                first.nextWaiter = null;
            } while (!AbstractQueuedSynchronizer.this.transferForSignal(first) && (first = this.firstWaiter) != null);

        }

        private void doSignalAll(AbstractQueuedSynchronizer.Node first) {
            this.lastWaiter = this.firstWaiter = null;

            AbstractQueuedSynchronizer.Node next;
            do {
                next = first.nextWaiter;
                first.nextWaiter = null;
                AbstractQueuedSynchronizer.this.transferForSignal(first);
                first = next;
            } while (next != null);

        }

        private void unlinkCancelledWaiters() {
            AbstractQueuedSynchronizer.Node t = this.firstWaiter;

            AbstractQueuedSynchronizer.Node next;
            for (AbstractQueuedSynchronizer.Node trail = null; t != null; t = next) {
                next = t.nextWaiter;
                if (t.waitStatus != -2) {
                    t.nextWaiter = null;
                    if (trail == null) {
                        this.firstWaiter = next;
                    } else {
                        trail.nextWaiter = next;
                    }

                    if (next == null) {
                        this.lastWaiter = trail;
                    }
                } else {
                    trail = t;
                }
            }

        }

        public final void signal() {
            if (!AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            } else {
                AbstractQueuedSynchronizer.Node first = this.firstWaiter;
                if (first != null) {
                    this.doSignal(first);
                }

            }
        }

        public final void signalAll() {
            if (!AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            } else {
                AbstractQueuedSynchronizer.Node first = this.firstWaiter;
                if (first != null) {
                    this.doSignalAll(first);
                }

            }
        }

        public final void awaitUninterruptibly() {
            AbstractQueuedSynchronizer.Node node = this.addConditionWaiter();
            int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
            boolean interrupted = false;

            while (!AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                LockSupport.park(this);
                if (Thread.interrupted()) {
                    interrupted = true;
                }
            }

            if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) || interrupted) {
                AbstractQueuedSynchronizer.selfInterrupt();
            }

        }

        private int checkInterruptWhileWaiting(AbstractQueuedSynchronizer.Node node) {
            return Thread.interrupted() ? (AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node) ? -1 : 1) : 0;
        }

        private void reportInterruptAfterWait(int interruptMode) throws InterruptedException {
            if (interruptMode == -1) {
                throw new InterruptedException();
            } else {
                if (interruptMode == 1) {
                    AbstractQueuedSynchronizer.selfInterrupt();
                }

            }
        }

        public final void await() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else {
                AbstractQueuedSynchronizer.Node node = this.addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
                int interruptMode = 0;

                while (!AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                    LockSupport.park(this);
                    if ((interruptMode = this.checkInterruptWhileWaiting(node)) != 0) {
                        break;
                    }
                }

                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }

                if (node.nextWaiter != null) {
                    this.unlinkCancelledWaiters();
                }

                if (interruptMode != 0) {
                    this.reportInterruptAfterWait(interruptMode);
                }

            }
        }

        public final long awaitNanos(long nanosTimeout) throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else {
                long deadline = System.nanoTime() + nanosTimeout;
                AbstractQueuedSynchronizer.Node node = this.addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);

                int interruptMode;
                for (interruptMode = 0; !AbstractQueuedSynchronizer.this.isOnSyncQueue(node); nanosTimeout = deadline - System.nanoTime()) {
                    if (nanosTimeout <= 0L) {
                        AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node);
                        break;
                    }

                    if (nanosTimeout > 1000L) {
                        LockSupport.parkNanos(this, nanosTimeout);
                    }

                    if ((interruptMode = this.checkInterruptWhileWaiting(node)) != 0) {
                        break;
                    }
                }

                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }

                if (node.nextWaiter != null) {
                    this.unlinkCancelledWaiters();
                }

                if (interruptMode != 0) {
                    this.reportInterruptAfterWait(interruptMode);
                }

                long remaining = deadline - System.nanoTime();
                return remaining <= nanosTimeout ? remaining : -9223372036854775808L;
            }
        }

        public final boolean awaitUntil(Date deadline) throws InterruptedException {
            long abstime = deadline.getTime();
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else {
                AbstractQueuedSynchronizer.Node node = this.addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
                boolean timedout = false;
                int interruptMode = 0;

                while (!AbstractQueuedSynchronizer.this.isOnSyncQueue(node)) {
                    if (System.currentTimeMillis() >= abstime) {
                        timedout = AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node);
                        break;
                    }

                    LockSupport.parkUntil(this, abstime);
                    if ((interruptMode = this.checkInterruptWhileWaiting(node)) != 0) {
                        break;
                    }
                }

                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }

                if (node.nextWaiter != null) {
                    this.unlinkCancelledWaiters();
                }

                if (interruptMode != 0) {
                    this.reportInterruptAfterWait(interruptMode);
                }

                return !timedout;
            }
        }

        public final boolean await(long time, TimeUnit unit) throws InterruptedException {
            long nanosTimeout = unit.toNanos(time);
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else {
                long deadline = System.nanoTime() + nanosTimeout;
                AbstractQueuedSynchronizer.Node node = this.addConditionWaiter();
                int savedState = AbstractQueuedSynchronizer.this.fullyRelease(node);
                boolean timedout = false;

                int interruptMode;
                for (interruptMode = 0; !AbstractQueuedSynchronizer.this.isOnSyncQueue(node); nanosTimeout = deadline - System.nanoTime()) {
                    if (nanosTimeout <= 0L) {
                        timedout = AbstractQueuedSynchronizer.this.transferAfterCancelledWait(node);
                        break;
                    }

                    if (nanosTimeout > 1000L) {
                        LockSupport.parkNanos(this, nanosTimeout);
                    }

                    if ((interruptMode = this.checkInterruptWhileWaiting(node)) != 0) {
                        break;
                    }
                }

                if (AbstractQueuedSynchronizer.this.acquireQueued(node, savedState) && interruptMode != -1) {
                    interruptMode = 1;
                }

                if (node.nextWaiter != null) {
                    this.unlinkCancelledWaiters();
                }

                if (interruptMode != 0) {
                    this.reportInterruptAfterWait(interruptMode);
                }

                return !timedout;
            }
        }

        final boolean isOwnedBy(AbstractQueuedSynchronizer sync) {
            return sync == AbstractQueuedSynchronizer.this;
        }

        protected final boolean hasWaiters() {
            if (!AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            } else {
                for (AbstractQueuedSynchronizer.Node w = this.firstWaiter; w != null; w = w.nextWaiter) {
                    if (w.waitStatus == -2) {
                        return true;
                    }
                }

                return false;
            }
        }

        protected final int getWaitQueueLength() {
            if (!AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            } else {
                int n = 0;

                for (AbstractQueuedSynchronizer.Node w = this.firstWaiter; w != null; w = w.nextWaiter) {
                    if (w.waitStatus == -2) {
                        ++n;
                    }
                }

                return n;
            }
        }

        protected final Collection<Thread> getWaitingThreads() {
            if (!AbstractQueuedSynchronizer.this.isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            } else {
                ArrayList<Thread> list = new ArrayList();

                for (AbstractQueuedSynchronizer.Node w = this.firstWaiter; w != null; w = w.nextWaiter) {
                    if (w.waitStatus == -2) {
                        Thread t = w.thread;
                        if (t != null) {
                            list.add(t);
                        }
                    }
                }

                return list;
            }
        }
    }


}
