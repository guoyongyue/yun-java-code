package com.yun.pattern.strategy.pay;

/**
 * @className: JDPay <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:08 <br/>
 * @author: yun <br/>
 */
public class JDPay extends  Payment {
    @Override
    public String getName() {
        return "京东白条";
    }

    @Override
    protected double queryBalance(String uid) {
        return 500;
    }
}
