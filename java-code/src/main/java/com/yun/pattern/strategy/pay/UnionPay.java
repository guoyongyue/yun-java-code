package com.yun.pattern.strategy.pay;

/**
 * @className: UnionPay <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:09 <br/>
 * @author: yun <br/>
 */
public class UnionPay extends Payment{
    @Override
    public String getName() {
        return "银联支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 120;
    }
}
