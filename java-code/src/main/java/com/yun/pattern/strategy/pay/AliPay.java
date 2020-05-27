package com.yun.pattern.strategy.pay;

/**
 * @className: AliPay <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:08 <br/>
 * @author: yun <br/>
 */
public class AliPay extends Payment {
    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    protected double queryBalance(String uid) {
        return 900;
    }
}
