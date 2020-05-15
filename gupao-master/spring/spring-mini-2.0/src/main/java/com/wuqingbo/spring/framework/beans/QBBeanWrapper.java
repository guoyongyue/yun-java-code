package com.wuqingbo.spring.framework.beans;

/**
 * Created by qingbowu.
 */
public class QBBeanWrapper {

    private Object wrappedInstance;
    private Class<?> wrappedClass;

    public QBBeanWrapper(Object wrappedInstance) {
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance(){
       return this.wrappedInstance;
   }

    /**
     * 返回代理以后的Class
     * 可能会是个$Proxy0
     */
    public Class<?> getWrappedClass(){
        return this.wrappedInstance.getClass();
    }
}
