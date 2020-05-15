package com.wuqingbo.spring.framework.aop.intercept;

/**
 * Created by qingbowu.
 */
public interface QBMethodInterceptor {

    public Object invoke(QBMethodInvocation invocation) throws Throwable;
}
