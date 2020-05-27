package com.yun.pattern.decorator;

/**
 * @className: BatterCakeDecorator <br/>
 * @description: <br/>
 * @date: 2020/5/23 20:23 <br/>
 * @author: yun <br/>
 */
public abstract class BatterCakeDecorator extends BatterCake{

    private BatterCake batterCake;

    public BatterCakeDecorator(BatterCake batterCake) {
        this.batterCake = batterCake;
    }

    /**
     * @methodName: getMsg <br/>
     * @params: <br/>
     * @description: <br/>
     * @date: 2020/5/23 20:26 <br/>
     * @author: yun <br/>
     */
    @Override
    protected String getMsg() {
        return this.batterCake.getMsg();
    }

    /**
     * @methodName: getPrice <br/>
     * @params: <br/>
     * @description: <br/>
     * @date: 2020/5/23 20:26 <br/>
     * @author: yun <br/>
     */
    @Override
    protected int getPrice() {
        return this.batterCake.getPrice();
    }
}
