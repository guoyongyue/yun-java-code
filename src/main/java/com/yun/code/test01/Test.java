package com.yun.code.test01;

public class Test extends Thread {
    public static void main(String[] args) throws Exception{
        Test test1  = new Test();
        Test test2  = new Test();

        test1.start();
        test2.start();

        System.out.println(Thread.currentThread().getName());

        synchronized(test1){
            test1.wait(100);
        }
        /**
         * Thread
         * static sleep(long)方法仅释放CPU使用权，锁仍然占用；线程被放入超时等待队列，与yield相比，它会使线程较长时间得不到运行。
         * static yield()方法仅释放CPU执行权，锁仍然占用，线程会被放入就绪队列，会在短时间内再次执行。
         * join()方法可以使得一个线程在另一个线程结束后再执行。可以来确保所有程序创建的线程在main()方法退出前结束。
         *
         *
         * Object 使用同步的方式进行使用，因为会涉及到锁的持有和释放
         * wait()方法会释放CPU执行权 和 占有的锁。
         * notify()唤醒
         * notifyAll()唤醒所有
         *
         *
         */
    }

    public void run(){
        int i=0;
        while (true){
            i++;
            //System.out.println(i+this.getName());
            if(i==5000){
                System.out.println(i+this.getName());
                return;
            }
        }

    }
}
