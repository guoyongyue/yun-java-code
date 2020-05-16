package com.yun.pattern.singleton.lazy;
/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/5/12 9:03<\br>
*/
public class LazyDoubleCheckSingleton {
    private volatile static LazyDoubleCheckSingleton instance=null;

    private LazyDoubleCheckSingleton(){

    }


    public static LazyDoubleCheckSingleton getInstance(){
        if(instance==null){
            synchronized (LazyDoubleCheckSingleton.class){
                if (instance==null){
                    instance=new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
