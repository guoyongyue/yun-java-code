package com.gupao.vip.pattern.delegate.simple;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class Boss {

    public void command(String command,Leader leader){
        leader.doSomething(command);
    }
}
