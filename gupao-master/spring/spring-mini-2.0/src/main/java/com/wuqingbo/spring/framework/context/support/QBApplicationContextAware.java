package com.wuqingbo.spring.framework.context.support;

import com.wuqingbo.spring.framework.context.QBApplicationContext;

/**
 * t通过解耦方式或得IOC容器的顶层设计
 * 后面将通过一个监听器去扫描所有的类，只要实现了此接口，
 * 将自动调用setApplicationContext()方法，从而将IOC容器注入到目标类中
 * Created by qingbowu.
 */
public interface QBApplicationContextAware {

    void setApplicationContext(QBApplicationContext applicationContext);
}
