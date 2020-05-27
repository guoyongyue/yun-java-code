package com.yun.pattern.strategy.pay;

/**
 * @className: PayState <br/>
 * @description: <br/>
 * @date: 2020/5/28 0:09 <br/>
 * @author: yun <br/>
 */
public class PayState {

    private int code;
    private Object data;
    private String msg;

    public PayState(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }


    public String toString() {
        return ("支付状态：[" + code + "]," + msg + ",交易详情：" + data);
    }

}
