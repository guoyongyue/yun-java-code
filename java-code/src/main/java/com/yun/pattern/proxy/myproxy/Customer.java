package com.yun.pattern.proxy.myproxy;

public class Customer implements BuyHouse {


    @Override
    public void buyHouse() {
        System.out.println("我有100万，能买一套100平的商品房吗？");
    }
}
