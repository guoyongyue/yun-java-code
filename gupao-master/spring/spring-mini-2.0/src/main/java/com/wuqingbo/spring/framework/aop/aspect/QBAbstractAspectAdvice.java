package com.wuqingbo.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * Created by qingbowu on 2019/4/22.
 */
public abstract class QBAbstractAspectAdvice {

    private Method aspectMethod;
    private Object aspectTarget;

    public QBAbstractAspectAdvice(Method aspectMethod,Object aspectTarget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }

    public  void invokeAdviceMethod(QBJoinPoint joinPoint,Object retValue,Throwable tx) throws Throwable{
        Class<?>[] parameterTypes = this.aspectMethod.getParameterTypes();
        if (null == parameterTypes || parameterTypes.length == 0){
            this.aspectMethod.invoke(aspectTarget);
        }else {
            Object [] args = new Object[parameterTypes.length];
            for (int i = 0; i< parameterTypes.length; i++) {
                if (parameterTypes[i] == QBJoinPoint.class){
                    args[i] = joinPoint;
                }else if (parameterTypes[i] == QBJoinPoint.class){
                    args[i] = tx;
                }else if (parameterTypes[i] == Object.class){
                    args[i] = retValue;
                }
            }
            this.aspectMethod.invoke(aspectTarget,args);
        }
    }
}
