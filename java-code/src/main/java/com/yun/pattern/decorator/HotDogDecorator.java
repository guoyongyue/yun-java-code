package com.yun.pattern.decorator;

/**
 * @className: HotDogDecorator <br/>
 * @description: <br/>
 * @date: 2020/5/23 20:34 <br/>
 * @author: yun <br/>
 */
public class HotDogDecorator extends BatterCakeDecorator {
    public HotDogDecorator(BatterCake batterCake) {
        super(batterCake);
    }

    /**
     * @methodName: getMsg <br/>
     * @params:  <br/>
     * @description: <br/>
     * @date: 2020/5/23 20:35 <br/>
     * @author: yun <br/>
     */
    @Override
    protected String getMsg() {
        return super.getMsg() + " + 1根热狗";
    }

    /**
     * @methodName: getPrice <br/>
     * @params:  <br/>
     * @description: <br/>
     * @date: 2020/5/23 20:35 <br/>
     * @author: yun <br/>
     */
    @Override
    protected int getPrice() {
        return super.getPrice() + 3;
    }
}
