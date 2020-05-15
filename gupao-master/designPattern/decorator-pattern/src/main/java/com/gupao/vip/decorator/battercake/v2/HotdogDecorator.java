package com.gupao.vip.decorator.battercake.v2;

/**
 * Created by qingbowu on 2019/3/22.
 */
public class HotdogDecorator extends BattercakeDecorator {
    public HotdogDecorator(Battercake battercake) {
        super(battercake);
    }

    @Override
    protected String getMsg() {
        return super.getMsg() + " + 1根热狗";
    }

    @Override
    protected int getPrice() {
        return super.getPrice() + 3;
    }
}
