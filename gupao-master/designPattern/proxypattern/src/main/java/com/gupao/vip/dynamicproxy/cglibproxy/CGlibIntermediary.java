package com.gupao.vip.dynamicproxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by qingbowu on 2019/3/12.
 */
public class CGlibIntermediary implements MethodInterceptor {

    public Object getInstance(Class<?> clazz) throws Exception{
        //相当于Proxy,代理工具类
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object invoke = methodProxy.invokeSuper(o,objects);
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
