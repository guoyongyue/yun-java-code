package com.gupao.vip.pattern.strategy.promotion;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class GroupBuyStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("拼团促销，满20人成团，全团享受团购价");
    }
}
