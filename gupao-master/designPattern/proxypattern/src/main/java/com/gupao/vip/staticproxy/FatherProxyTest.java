package com.gupao.vip.staticproxy;

/**
 * Created by qingbowu on 2019/3/11.
 */
public class FatherProxyTest {

    public static void main(String[] args) {
        Father father = new Father(new Son());
        father.findGirlFriend();
    }
}
