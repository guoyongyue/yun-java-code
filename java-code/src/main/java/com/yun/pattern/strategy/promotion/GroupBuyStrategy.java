package com.yun.pattern.strategy.promotion;

public class GroupBuyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("拼团促销，满20人成团，全团享受团购价");
    }
}
