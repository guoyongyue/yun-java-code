package com.gupao.vip.pattern.strategy.promotion;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class CashBackStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现促销，返回的金额转至支付宝账户");
    }
}
