package com.gupao.vip.decorator.battercake.v1;

/**
 * Created by qingbowu on 2019/3/22.
 */
public class BattercakeTest {

    public static void main(String[] args) {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getMsg()+",共"+battercake.getPrice() + "元");

        Battercake battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getMsg()+","+battercakeWithEgg.getPrice() + "元");

        Battercake battercakeWithEggAndHotdog = new BattercakeWithEggAndHotdog();
        System.out.println(battercakeWithEggAndHotdog.getMsg()+","+battercakeWithEggAndHotdog.getPrice() + "元");
    }
}
