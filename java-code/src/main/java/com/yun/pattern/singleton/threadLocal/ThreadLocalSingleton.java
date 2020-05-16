package com.yun.pattern.singleton.threadLocal;

public class ThreadLocalSingleton {

    private static final ThreadLocal<ThreadLocalSingleton> instance = new ThreadLocal<ThreadLocalSingleton>(){
      @Override
      protected ThreadLocalSingleton initialValue(){
          return new ThreadLocalSingleton();
      }
    };

    private ThreadLocalSingleton(){
    }

    /**
     * 在主线程调用时能保证单例，在新的thread中保证不了线程安全
     * @return
     */
    public static ThreadLocalSingleton getInstance(){
        return ThreadLocalSingleton.instance.get();
    }
}
