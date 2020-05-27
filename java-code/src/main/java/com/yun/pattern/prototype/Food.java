package com.yun.pattern.prototype;

import java.io.Serializable;

/**
 * @methodName: <br/>
 * @params: <br/>
 * @description: <br/>
 * @date: 2020/5/24 12:43 <br/>
 * @author: yun <br/>
 */
public class Food implements Serializable {

    public int times=3;//吃的餐数
    public String names="杂草";//食物名字

    /**
     * @methodName: <br/>
     * @params: <br/>
     * @description: <br/>
     * @date: 2020/5/24 12:43 <br/>
     * @author: yun <br/>
     */
    public void eat(){
        this.names="嫩草";
        this.times = this.times*2;
    }
}
