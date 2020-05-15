package com.gupao.vip.pattern.strategy.promotion;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class CouponStrategy implements PromotionStrategy {

    @Override
    public void doPromotion() {
        System.out.println("领取优惠券，商品价格直接减去优惠券面值抵扣");
    }
}
