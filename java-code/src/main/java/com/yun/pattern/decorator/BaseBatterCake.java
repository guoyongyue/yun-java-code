package com.yun.pattern.decorator;

/**
 * @class name: BaseBatterCake <br/>
 * @description: <br/>
 * @date: 2020/5/23 19:41<br/>
 * @author: yun<br />
 */
public class BaseBatterCake extends BatterCake {

    /**
     * @paramnull
     * @method name: a <br/>
     * @description:
     * @date: 2020/5/23 19:45<br/>
     * @author: yun
     */
    protected String getMsg() {
        return "煎饼";
    }

    /**
     * @methodName: getPrice <br/>
     * @params:
     * @description:
     * @date: 2020/5/23 20:20<br/>
     * @author: yun
     */
    @Override
    protected int getPrice() {
        return 5;
    }
}
