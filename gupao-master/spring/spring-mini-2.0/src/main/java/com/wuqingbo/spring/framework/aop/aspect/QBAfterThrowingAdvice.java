package com.wuqingbo.spring.framework.aop.aspect;

import com.wuqingbo.spring.framework.aop.intercept.QBMethodInterceptor;
import com.wuqingbo.spring.framework.aop.intercept.QBMethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by qingbowu.
 */
public class QBAfterThrowingAdvice extends QBAbstractAspectAdvice implements QBMethodInterceptor {

    private String throwName;


    public QBAfterThrowingAdvice(Method method, Object aspectTarget) {
        super(method, aspectTarget);
    }


    @Override
    public Object invoke(QBMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        } catch (Throwable throwable) {
            invokeAdviceMethod(mi,null,throwable.getCause());
            throw throwable;
        }
    }

    public void setThrowName(String throwName) {
        this.throwName = throwName;
    }
}
