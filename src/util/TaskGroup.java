package util;

import java.util.concurrent.*;

public class TaskGroup {

    private static final BlockingQueue<Runnable> queueHandleTask = new LinkedBlockingQueue<Runnable>();
    private static ThreadPoolExecutor threadPoolExecutor = null;


    public static class SingletonInner{
           private static TaskGroup taskGroup = new TaskGroup(100);
    }

    public static TaskGroup getInstance(){
        return SingletonInner.taskGroup;
    }

    private TaskGroup(int maxPoolSize){
        threadPoolExecutor = new ThreadPoolExecutor(2, maxPoolSize,
                60L, TimeUnit.SECONDS, queueHandleTask);
    }

    final public void sync(final Runnable...aoRunnables) {
        if ( aoRunnables == null ) {
            return;
        }
        final int iLen = aoRunnables.length;
        if ( iLen == 0 ) {
            return;
        }
        // 同步计数对象
        int iCount = iLen - 1; // 我们将第一个任务放在调用线程执行即可
        final CountDownLatch oCountDown = new CountDownLatch(iCount);
        // 开始逐个添加异步执行的任务
        for ( ;; ) {
            if ( iCount == 0 ) {
                // 第一个任务直接执行即可
                try {
                    if ( aoRunnables[0] != null ) aoRunnables[0].run();
                } catch (Throwable t) {
                    System.out.println("sync, run first task error:" + t.toString());
                }

                // 等待其他任务全部结束
                try {
                    oCountDown.await();
                } catch (InterruptedException e) {
                }
                break;
            }
            // 不是最后一个任务添加到线程池运行
            final Task oTask = new Task(aoRunnables[iCount--], oCountDown);
            try {
                threadPoolExecutor.execute(oTask);
            } catch (RejectedExecutionException e) {
                // 拒绝的任务，直接在调用线程运行
                System.out.println("sync reject error:{}" + e.toString());
                oTask.run();
            } catch (Exception e) {
                System.out.println("sync error:{}" + e.toString());
                oTask.run();
            }
        }
    }

    /**
     * 内部使用的任务类，包装一个等待执行的任务
     */
    private static final class Task implements Runnable {
        private Runnable moRunnable;
        private CountDownLatch moCountDown;
        /**
         * 构造
         * @param aoRunnable 真正需要执行的Runnable
         * @param aoCountDown 需要同步的计数对象
         */
        public Task(final Runnable aoRunnable, final CountDownLatch aoCountDown) {
            assert aoCountDown != null;
            moRunnable = aoRunnable;
            moCountDown = aoCountDown;
        }

        @Override
        public void run() {
            try {
                if ( moRunnable != null ) moRunnable.run();
            } catch (Throwable t) {
                System.out.println("Task.run error:"+ t.toString());
            }
            moCountDown.countDown();
        }
    }




}
