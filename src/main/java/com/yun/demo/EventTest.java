package com.yun.demo;

// main 方法
public class EventTest {
    public static void main(String[] args) {

        if(1 !=3 && 1 !=15 ){
            System.out.println("-");
        }else {
            System.out.println("-----df");
        }
        // 初始化发布者实体类
        PublishService publishService = new PublishService();
        // 发送消息
        publishService.sendMessage();
    }


}