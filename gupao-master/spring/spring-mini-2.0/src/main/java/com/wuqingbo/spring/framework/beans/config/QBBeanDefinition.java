package com.wuqingbo.spring.framework.beans.config;

import lombok.Data;

/**
 * Created by qingbowu.
 */
@Data
public class QBBeanDefinition {

    private String beanClassName;

    private boolean lazyInit = false;

    private String factoryBeanName;

    private boolean isSingleton = true;
}
