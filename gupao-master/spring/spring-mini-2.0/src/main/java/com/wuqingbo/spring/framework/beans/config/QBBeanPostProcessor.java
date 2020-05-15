package com.wuqingbo.spring.framework.beans.config;

/**
 * Created by qingbowu.
 */
public class QBBeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws Exception {
        return bean;
    }
}
