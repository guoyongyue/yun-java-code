package com.gupao.vip.pattern.delegate.simple;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class EmployeeB implements IEmployee {


    @Override
    public void doSomeThing(String command) {
        System.out.println("我是员工B，我开始干活了，我擅长写js，执行"+command);
    }
}
