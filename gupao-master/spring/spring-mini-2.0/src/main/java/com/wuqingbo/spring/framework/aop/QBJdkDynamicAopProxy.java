package com.wuqingbo.spring.framework.aop;

import com.wuqingbo.spring.framework.aop.intercept.QBMethodInvocation;
import com.wuqingbo.spring.framework.aop.support.QBAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * jdk动态代理
 * Created by qingbowu
 */
public class QBJdkDynamicAopProxy implements QBAopProxy,InvocationHandler{

    private QBAdvisedSupport advised;

    public QBJdkDynamicAopProxy(QBAdvisedSupport config) {
        this.advised = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,this.advised.getTargetClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, this.advised.getTargetClass());
        QBMethodInvocation invocation = new QBMethodInvocation(proxy,this.advised.getTargetObje(),method,args,this.advised.getTargetClass(),chain);
        return invocation.proceed();
    }
}
