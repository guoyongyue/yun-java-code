package com.yun.pattern.decorator;

/**
 * @className: EggDecorator <br/>
 * @description: <br/>
 * @date: 2020/5/23 20:33 <br/>
 * @author: yun <br/>
 */
public class EggDecorator extends BatterCakeDecorator {

    public EggDecorator(BatterCake batterCake) {
        super(batterCake);
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
        return super.getMsg() + " + 1个鸡蛋";
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
        return super.getPrice() + 2;
    }
}
