package com.yun.pattern.observer.guava;

import com.google.common.eventbus.EventBus;

/**
 * @className: Main <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:06 <br/>
 * @author: yun <br/>
 */
public class Main {

    public static void main(String[] args) {
        // 消息总线
        EventBus eventBus = new EventBus();

        // 把 GuavaEvent 注册到消息总线中
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("Tom");

        /**
         * 类似于从Struts到SpringMVC的升级
         * 前者面向的是类，后者面向的是方法
         *
         * GPer和Event场景面向的是类   Guava面向的是方法
         *
         *
         * Guava ：能够轻松落地观察者模式的一种解决方案
         */
    }

}
