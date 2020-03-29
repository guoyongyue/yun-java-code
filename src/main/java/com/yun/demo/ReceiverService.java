package com.yun.demo;

/**
 * 事件接收，具体处理方法的类
 *
 * @author tomxin
 * @since 2018-12-16
 */
public class ReceiverService {

    /**
     * 接收方法
     *
     * @param message
     */
    public void receive(String message) {
        System.out.println("receiver message" + message);
    }
}