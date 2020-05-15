package com.gupao.vip.pattern.singlerton.hungry;

/**
 * 第二种写法
 * Created by qingbowu on 2019/3/10.
 */
public class HungryStaticSingleton {

    /**
     * 这里加final修饰是为了防止利用反射机制覆盖该实例
     */
    private static final HungryStaticSingleton hungryStaticSingleton ;

    static {
        hungryStaticSingleton = new HungryStaticSingleton();
    }

    private HungryStaticSingleton(){
    }

    public static HungryStaticSingleton getInstance(){
        return hungryStaticSingleton;
    }
}
