package com.yun.pattern.adapter.poweradapter;

/**
 * @class name:  PowerAdapterTest <br/>
 * @description: <br/>
 * @date: 2020/5/23 11:50<br/>
 * @author: yun<br />
 */
public class PowerAdapterTest {

    /**
     * @param args
     * @method name: main <br/>
     * @description:
     * @date: 2020/5/23 11:50<br/>
     * @author: yun
     */
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5V();
    }
}
