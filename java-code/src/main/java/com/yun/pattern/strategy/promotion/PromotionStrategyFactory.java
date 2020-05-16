package com.yun.pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

public class PromotionStrategyFactory {
    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<String, PromotionStrategy>();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.COUPON, new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.CASHBACK, new CashBackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.GROUPBUY, new GroupBuyStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.DEFAULT, new EmptyStrategy());
    }


    public static PromotionStrategy getPromotionStrategy(String promotionStrategyKey) {
        return PROMOTION_STRATEGY_MAP.containsKey(promotionStrategyKey) ?
                PROMOTION_STRATEGY_MAP.get(promotionStrategyKey) :
                PROMOTION_STRATEGY_MAP.get(PromotionStrategyKey.DEFAULT);
    }

    public interface PromotionStrategyKey {
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
        String DEFAULT = "DEFAULT";
    }
}