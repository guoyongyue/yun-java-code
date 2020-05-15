package com.gupao.vip.pattern.strategy.pay.payport;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class JDPay extends Payment{

    @Override
    public String getName() {
        return "京东白条";
    }

    @Override
    public double queryBanlance(String uid) {
        return 500;
    }
}
