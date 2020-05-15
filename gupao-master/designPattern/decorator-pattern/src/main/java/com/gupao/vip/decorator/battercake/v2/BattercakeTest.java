package com.gupao.vip.decorator.battercake.v2;


/**
 * Created by qingbowu on 2019/3/22.
 */
public class BattercakeTest {

    public static void main(String[] args) {
        //先做一个最基本的煎饼
        Battercake battercake = new BaseBattercake();
        //给这个最基本的煎饼加一个鸡蛋
        battercake = new EggDecorator(battercake);
        //再加一根香肠
        battercake = new HotdogDecorator(battercake);
        //感觉不爽，还是再加一个鸡蛋
        battercake = new EggDecorator(battercake);
        System.out.println(battercake.getMsg()+",共"+battercake.getPrice() + "元");


        //如果还需要加其他的东西，再建一个对应的装饰类就可以了
    }
}
