package com.gupao.vip.pattern.adapter.poweradapter;

/**
 * Created by qingbowu on 2019/3/19.
 */
public class PowerAdapterTest {

    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5V();
    }
}
