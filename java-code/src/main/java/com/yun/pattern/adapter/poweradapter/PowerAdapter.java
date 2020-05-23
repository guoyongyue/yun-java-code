package com.yun.pattern.adapter.poweradapter;

/**
 * @class name:  PowerAdapter <br/>
 * @description: <br/>
 * @date: 2020/5/23 11:51<br/>
 * @author: yun<br />
 */
public class PowerAdapter implements DC5 {

    private AC220 ac220;


    /**
     * @param ac220
     * @method name: PowerAdapter  <br/>
     * @description:
     * @date: 2020/5/23 11:51<br/>
     * @author: yun
     */
    public PowerAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    /**
     * @param
     * @method name: outputDC5V <br/>
     * @description:
     * @date: 2020/5/23 11:52<br/>
     * @author: yun
     */
    @Override
    public int outputDC5V() {
        int intputPower = this.ac220.outputAC220V();
        int outputPower = intputPower / 44;
        System.out.println("使用PowerAdapter输入电流" + intputPower + "V,输出电流" + outputPower + "V");
        return outputPower;
    }
}
