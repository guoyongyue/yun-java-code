package com.gupao.vip.decorator.battercake.v1;

/**
 * Created by qingbowu on 2019/3/22.
 */
public class BattercakeWithEggAndHotdog extends Battercake{
    @Override
    public String getMsg() {
        return super.getMsg() + "加一个鸡蛋，再加一根热狗";
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 4;
    }
}
