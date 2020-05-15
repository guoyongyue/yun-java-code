package com.gupao.vip.pattern.strategy.pay.payport;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class AliPay extends Payment {

    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    public double queryBanlance(String uid) {
        return 2000;
    }
}
