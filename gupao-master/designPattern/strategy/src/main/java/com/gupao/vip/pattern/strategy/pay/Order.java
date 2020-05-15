package com.gupao.vip.pattern.strategy.pay;

import com.gupao.vip.pattern.strategy.pay.payport.PayStrategy;
import com.gupao.vip.pattern.strategy.pay.payport.Payment;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class Order {

    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    //完美地解决了 switch 的过程，不需要在代码逻辑中写 switch 了
    //更不需要写 if else if
    public MsgResult pay(){
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public MsgResult pay(String paykey){
        Payment payment = PayStrategy.getPayStrategy(paykey);
        System.out.println("欢迎使用"+payment.getName());
        System.out.println("本次交易金额为："+amount);
        return payment.pay(uid,amount);
    }

}
