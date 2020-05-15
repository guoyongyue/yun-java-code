package com.wuqingbo.spring.framework.aop.aspect;

import java.lang.reflect.Method;

/**
 * Created by qingbowu on 2019/4/23.
 */
public interface QBJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
