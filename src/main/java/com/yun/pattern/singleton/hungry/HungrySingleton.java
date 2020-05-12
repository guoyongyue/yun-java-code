package com.yun.pattern.singleton.hungry;
/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/5/12 9:06<\br>
*/
public class HungrySingleton {
    private final static HungrySingleton instance=new HungrySingleton();

    private HungrySingleton(){
    }

    public HungrySingleton getInstance(){
        return instance;
    }
}
