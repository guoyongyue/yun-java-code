package com.gupao.vip.dynamicproxy.jdkproxy;

import java.util.Random;

/**
 * Created by qingbowu on 2019/3/11.
 */
public class OrderImpl implements IOrder{

    @Override
    public void addOrder() {
        System.out.println("我买了99块钱的东西");
    }

    @Override
    public String getOrderId() {
        System.out.println("获取订单号");
        return "111111111112312424235235";
    }
}
