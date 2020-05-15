package com.gupao.vip.pattern.singlerton.seriable;

import java.io.Serializable;

/**
 * 序列化的方式创建对象可破坏单例
 * 重写readResolve方法即可解决
 * Created by qingbowu on 2019/3/10.
 */
public class SeriableSingleton implements Serializable {
    private static final SeriableSingleton SINGLETON  = new SeriableSingleton();

    private SeriableSingleton(){ }

    public static SeriableSingleton getInstance(){
        return SINGLETON;
    }

    //重写readResolve方法只不过是覆盖了反序列化创建出来的对象
    //但依旧是创建了两次对象，发生在JVM层面，相对来说安全
    //之前反序列化出来的对象会被GC回收
    private Object readResolve(){
        return SINGLETON;
    }

}
