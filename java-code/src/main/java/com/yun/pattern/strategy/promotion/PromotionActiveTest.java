package com.yun.pattern.strategy.promotion;

/**
 * 策略模式+简单工厂模式
 */
public class PromotionActiveTest {
    public static void main(String[] args) {

        String promotionKey = "GROUPBUY";
        new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey)).execute();

    }

}