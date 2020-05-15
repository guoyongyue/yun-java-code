package com.wuqingbo.spring.framework.aop;

/**
 * Created by qingbowu
 */
public interface QBAopProxy {


    Object getProxy();


    Object getProxy( ClassLoader classLoader);
}
