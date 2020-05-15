package com.gupao.vip.pattern.strategy.promotion;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class PromotionActivTest {

//    public static void main(String[] args) {
//        PromotionActivity activity618 = new PromotionActivity(new CashBackStrategy());
//        PromotionActivity activity1111 = new PromotionActivity(new CouponStrategy());
//
//        activity618.execute();
//        activity1111.execute();
//    }

//    public static void main(String[] args) {
//        PromotionActivity promotionActivity = null;
//        String promotionKey = "COUPON";
//        if (StringUtils.equals(promotionKey,"COUPON")){
//            promotionActivity = new PromotionActivity(new CouponStrategy());
//        }else if (StringUtils.equals(promotionKey,"CASHBACK")){
//            promotionActivity = new PromotionActivity(new CashBackStrategy());
//        }// ...
//
//        promotionActivity.execute();
//    }


    public static void main(String[] args) {
        String promotionKey = "GROUPBUY";
        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey));
        promotionActivity.execute();
    }
}
