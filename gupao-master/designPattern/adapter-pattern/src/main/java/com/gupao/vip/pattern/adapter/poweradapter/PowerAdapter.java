package com.gupao.vip.pattern.adapter.poweradapter;

/**
 * Created by qingbowu on 2019/3/19.
 */
public class PowerAdapter implements DC5 {

    private AC220 ac220;

    public PowerAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int outputDC5V() {
        int intputPower = this.ac220.outputAC220V();
        int outputPower = intputPower / 44;
        System.out.println("使用PowerAdapter输入电流" + intputPower + "V,输出电流" + outputPower + "V");
        return outputPower;
    }
}
