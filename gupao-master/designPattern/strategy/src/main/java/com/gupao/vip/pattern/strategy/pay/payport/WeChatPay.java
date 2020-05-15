package com.gupao.vip.pattern.strategy.pay.payport;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class WeChatPay extends Payment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    public double queryBanlance(String uid) {
        return 1000;
    }
}
