package com.gupao.vip.decorator.battercake.v1;

/**
 * Created by qingbowu on 2019/3/22.
 */
public class BattercakeWithEgg extends Battercake {
    @Override
    public String getMsg() {
        return super.getMsg() + "加1个鸡蛋" ;
    }

    @Override
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
