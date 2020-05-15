package com.wuqingbo.spring.framework.beans;

/**
 * 单例工厂的顶层设计
 * Created by qingbowu.
 */
public interface QBBeanFactory {

    /**
     * 根据beanName从IOC容器中获得一个实例bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws Exception;

    /**
     * 根据beanClass从IOC容器中获得一个实例bean
     * @param beanClass
     * @return
     */
    Object getBean(Class<?> beanClass) throws Exception;

    /**
     * 获取所有实例的个数
     * @return
     */
    int getBeanDefinitionCount();

    /**
     * 获取所有的类名
     * @return
     */
    String[] getBeanDefinitionNames();
}
