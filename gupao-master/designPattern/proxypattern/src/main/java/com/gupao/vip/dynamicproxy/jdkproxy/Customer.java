package com.gupao.vip.dynamicproxy.jdkproxy;

/**
 * Created by qingbowu on 2019/3/11.
 */
public class Customer implements BuyHouse {


    @Override
    public void buyHouse() {
        System.out.println("我有100万，能买一套100平的商品房吗？");
    }
}
