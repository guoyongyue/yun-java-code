package com.yun.pattern.singleton.hungry;

public class HungryStaticSingleton {
    private static final HungryStaticSingleton hungryStaticSingleton;

    static {
        hungryStaticSingleton=new HungryStaticSingleton();
    }

    private HungryStaticSingleton(){

    }

    public static HungryStaticSingleton getHungryStaticSingleton(){
        return hungryStaticSingleton;
    }
}
