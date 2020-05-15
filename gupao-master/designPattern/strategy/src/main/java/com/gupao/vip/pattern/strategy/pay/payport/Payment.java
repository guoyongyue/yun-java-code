package com.gupao.vip.pattern.strategy.pay.payport;

import com.gupao.vip.pattern.strategy.pay.MsgResult;

/**
 * 支付渠道
 * Created by qingbowu on 2019/3/15.
 */
public abstract class Payment {

    public abstract String getName();

    public abstract double queryBanlance(String uid);

    public MsgResult pay(String uid,double amount){
        if (queryBanlance(uid) < amount){
            return new MsgResult(500,"支付失败","余额不足");
        }
        return new MsgResult(200,"支付成功","支付金额:"+amount);
    }

}
