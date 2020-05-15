package com.wuqingbo;

import com.wuqingbo.spring.demo.controller.DemoController;
import com.wuqingbo.spring.demo.service.impl.DemoService;
import com.wuqingbo.spring.framework.context.QBApplicationContext;

/**
 * Created by qingbowu on 2019/4/16.
 */
public class Test {

    public static void main(String[] args) {
        QBApplicationContext context = new QBApplicationContext("classpath:application.properties");
        try {
//            DemoService bean = (DemoService)context.getBean("demoService");
            Object bean1 = context.getBean(DemoController.class);
            System.out.println(bean1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
