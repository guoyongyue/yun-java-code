package com.yun.pattern.delegate.simple;

public class EmployeeB implements IEmployee {
    @Override
    public void doing(String common) {
        System.out.println("我擅长架构我正在"+common);
    }
}
