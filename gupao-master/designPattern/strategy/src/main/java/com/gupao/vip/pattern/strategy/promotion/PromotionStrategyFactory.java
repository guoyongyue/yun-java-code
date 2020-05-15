package com.gupao.vip.pattern.strategy.promotion;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class PromotionStrategyFactory {

    private static Map<String,PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<String,PromotionStrategy>();

    static {
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.COUPON,new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.CASHBACK,new CashBackStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionStrategyKey.GROUPBUY,new GroupBuyStrategy());
    }

    private static final PromotionStrategy NON_PROMOTION = new EmptyStrategy();

    public static PromotionStrategy getPromotionStrategy(String promotionStrategyKey){
        PromotionStrategy promotionStrategy = PROMOTION_STRATEGY_MAP.get(promotionStrategyKey);
        return promotionStrategy == null ? NON_PROMOTION : promotionStrategy;
    }

    public interface PromotionStrategyKey{
        String COUPON = "COUPON";
        String CASHBACK = "CASHBACK";
        String GROUPBUY = "GROUPBUY";
    }
}
