package com.gupao.vip.dynamicproxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by qingbowu on 2019/3/11.
 */
public class OrderProxy implements InvocationHandler {

    private IOrder order;

    public OrderProxy(IOrder order){
        this.order = order;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        String methodName = method.getName();
        if (methodName.startsWith("add")){
            System.out.println("下单超过100块打7折哟！");
        }
        return method.invoke(order,args);
    }
}
