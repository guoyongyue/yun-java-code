package com.gupao.vip.pattern.delegate.simple;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class DelegateTest {

    public static void main(String[] args) {
        new Boss().command("sign",new Leader());
        new Boss().command("js",new Leader());
    }
}
