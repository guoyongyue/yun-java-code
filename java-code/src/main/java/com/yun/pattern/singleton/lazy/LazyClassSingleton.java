package com.yun.pattern.singleton.lazy;

import java.io.Serializable;

/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/5/12 8:40<\br>
*/
public class LazyClassSingleton implements Serializable {

    private LazyClassSingleton(){
        if(InnerClass.SingletonInstance!=null){
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    public static LazyClassSingleton getInstance(){
        return InnerClass.SingletonInstance;
    }

    private static class InnerClass{
        private static final LazyClassSingleton SingletonInstance = new LazyClassSingleton();
    }

    private Object readResolve(){
        return InnerClass.SingletonInstance;
    }
}
