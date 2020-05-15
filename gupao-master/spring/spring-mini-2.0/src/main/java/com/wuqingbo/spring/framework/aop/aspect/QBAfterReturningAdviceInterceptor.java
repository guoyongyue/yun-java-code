package com.wuqingbo.spring.framework.aop.aspect;

import com.wuqingbo.spring.framework.aop.intercept.QBMethodInterceptor;
import com.wuqingbo.spring.framework.aop.intercept.QBMethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by qingbowu.
 */
public class QBAfterReturningAdviceInterceptor extends QBAbstractAspectAdvice implements QBMethodInterceptor {

    private QBJoinPoint joinPoint;


    public QBAfterReturningAdviceInterceptor(Method method, Object aspectTarget) {
        super(method, aspectTarget);
    }

    @Override
    public Object invoke(QBMethodInvocation mi) throws Throwable {
        this.joinPoint = mi;
        Object returnValue = mi.proceed();
        this.afterReturning(returnValue,mi.getMethod(),mi.getArguments(),mi.getThis());
        return returnValue;
    }

    private void afterReturning(Object returnValue, Method method, Object arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint,returnValue,null);
    }
}
