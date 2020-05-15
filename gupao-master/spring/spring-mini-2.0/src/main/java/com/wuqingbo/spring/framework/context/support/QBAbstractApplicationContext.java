package com.wuqingbo.spring.framework.context.support;

/**
 * IOC容器实现的顶层设计,不管是ClassPathXMLApplicationContext还是AnnotationAutowiredApplicationContext
 * 都基于这个类做扩展
 * Created by qingbowu.
 */
public abstract class QBAbstractApplicationContext {

    public void refresh()throws Exception {};
}
