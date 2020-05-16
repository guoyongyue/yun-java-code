package com.yun.pattern.strategy.promotion;

public class PromotionActivity {
    private PromotionStrategy promotionStrategy;

    public PromotionActivity(PromotionStrategy promotionStrategy){
        this.promotionStrategy=promotionStrategy;
    }

    public void execute(){
        this.promotionStrategy.doPromotion();
    }
}