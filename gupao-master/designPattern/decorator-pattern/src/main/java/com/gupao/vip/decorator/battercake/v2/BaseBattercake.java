package com.gupao.vip.decorator.battercake.v2;

/**
 * Created by qingbowu on 2019/3/22.
 */
public class BaseBattercake extends Battercake {
    @Override
    protected String getMsg() {
        return "煎饼";
    }

    @Override
    protected int getPrice() {
        return 5;
    }
}
