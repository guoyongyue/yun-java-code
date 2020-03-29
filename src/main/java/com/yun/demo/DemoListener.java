package com.yun.demo;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 事件监听者
 *
 * @author tomxin
 * @since 2018-12-16
 */
public class DemoListener implements ApplicationListener<ApplicationEvent> {

    private ReceiverService receiverService;

    /**
     * 构造函数
     */
    public DemoListener() {
        receiverService = new ReceiverService();
    }

    /**
     * 实现ApplicationListener的onApplicationEvent方法
     *
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof DemoEvent) {
            receiverService.receive(((DemoEvent) event).getMessage());
        } else {
            System.out.println("event class " + event.getClass());
        }
    }
}
