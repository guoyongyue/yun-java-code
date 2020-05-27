package com.yun.pattern.decorator;

/**
 * @class name: BatterCake <br/>
 * @description: 装饰者模式<br/>
 * @date: 2020/5/23 19:35<br/>
 * @author: yun<br />
 */
public abstract class BatterCake {
    /**
     * @param
     * @method name: getMsg <br/>
     * @description:
     * @date: 2020/5/23 19:38<br/>
     * @author: yun
     */
    protected abstract String getMsg();

    /**
     * @param
     * @method name: getPrice <br/>
     * @description:
     * @date: 2020/5/23 19:38<br/>
     * @author: yun
     */
    protected abstract int getPrice();
}
