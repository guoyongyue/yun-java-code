package com.gupao.vip.pattern.strategy.promotion;

/**
 * 无优惠
 * Created by qingbowu on 2019/3/15.
 */
public class EmptyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("无优惠");
    }
}
