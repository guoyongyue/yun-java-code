package com.yun.pattern.delegate.simple;

public class EmployeeA implements IEmployee {
    @Override
    public void doing(String common) {
        System.out.println("我擅长加密我正在"+common);
    }
}
