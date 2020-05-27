package com.yun.pattern.strategy.pay;

/**
 * @className: WechatPay <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:09 <br/>
 * @author: yun <br/>
 */
public class WechatPay extends Payment {
    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    protected double queryBalance(String uid) {
        return 256;
    }
}
