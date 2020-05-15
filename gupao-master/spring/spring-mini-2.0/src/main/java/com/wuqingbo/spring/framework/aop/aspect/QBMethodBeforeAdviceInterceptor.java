package com.wuqingbo.spring.framework.aop.aspect;

import com.wuqingbo.spring.framework.aop.intercept.QBMethodInterceptor;
import com.wuqingbo.spring.framework.aop.intercept.QBMethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by qingbowu.
 */
public class QBMethodBeforeAdviceInterceptor extends QBAbstractAspectAdvice implements QBMethodInterceptor {

    private QBJoinPoint joinPoint;


    public QBMethodBeforeAdviceInterceptor(Method method, Object aspectTarget) {
        super(method, aspectTarget);
    }

    private void before(Method method,Object[] args,Object target) throws Throwable{
//        method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint,null,null);
    }

    @Override
    public Object invoke(QBMethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到joinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
