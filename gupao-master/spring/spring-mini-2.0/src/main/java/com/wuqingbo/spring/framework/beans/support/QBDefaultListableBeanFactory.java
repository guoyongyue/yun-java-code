package com.wuqingbo.spring.framework.beans.support;

import com.wuqingbo.spring.framework.beans.config.QBBeanDefinition;
import com.wuqingbo.spring.framework.context.support.QBAbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IOC容器实现的一些公共逻辑以及IOC容器的默认实现策略
 * Created by qingbowu.
 */
public class QBDefaultListableBeanFactory extends QBAbstractApplicationContext {

    //存储注册信息的BeanDefinition，伪IOC容器
    protected final Map<String, QBBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
}
