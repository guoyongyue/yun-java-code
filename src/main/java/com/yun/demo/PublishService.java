package com.yun.demo;

import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * 消息发送实体类
 *
 * @author tomxin
 * @since 2018-12-16
 */
public class PublishService {

    /**
     * 发送消息的方法
     */
    public void sendMessage() {
        // 创建一个发送信息的实体类
        ApplicationEventMulticaster applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        // 将Listener实例化，并加载到监听者列表中。
        DemoListener demoListener = new DemoListener();
        applicationEventMulticaster.addApplicationListener(demoListener);
        // 承载消息的实体类，封装信息内容，消息实体类必须继承ApplicationEvent
        DemoEvent event = new DemoEvent("tomxin");
        event.setMessage("demo event message");
        // 发送消息
        applicationEventMulticaster.multicastEvent(event);
    }
}
