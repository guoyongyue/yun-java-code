package com.yun.pattern.strategy.promotion;

public class CashBackStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现促销，返回的金额转至支付宝账户");
    }
}
