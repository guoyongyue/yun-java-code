package com.yun.demo;


import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件实体类 必须继承ApplicationEvent类
 *
 * @author tomxin
 * @since 2018-12-16
 */
public class DemoEvent extends ApplicationEvent {

    private String message;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public DemoEvent(Object source) {
        super(source);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}







