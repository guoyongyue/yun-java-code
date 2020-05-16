package com.yun.pattern.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Intermediary implements InvocationHandler {

    private BuyHouse buyHousePerson;

    public Object getInstance(BuyHouse buyHousePerson) throws Exception {
        this.buyHousePerson = buyHousePerson;
        Class<?> cls = buyHousePerson.getClass();
        return Proxy.newProxyInstance(cls.getClassLoader(),cls.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(buyHousePerson, args);
        after();
        return invoke;
    }

    private void before(){
        System.out.println("需要买房吗？我手上有大量房源");
    }

    private void after(){
        System.out.println("没问题，不过你得先交茶水费哟！");
    }
}
