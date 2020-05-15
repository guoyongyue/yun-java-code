package com.gupao.vip.dynamicproxy.jdkproxy;

import java.lang.reflect.Proxy;

/**
 * Created by qingbowu on 2019/3/11.
 */
public class OrderProxyTest {

    public static void main(String[] args) {
        IOrder order = new OrderImpl();
        OrderProxy orderProxy = new OrderProxy(order);
        IOrder proxy =(IOrder) Proxy.newProxyInstance(order.getClass().getClassLoader(), order.getClass().getInterfaces(), orderProxy);

        proxy.addOrder();
    }
}
