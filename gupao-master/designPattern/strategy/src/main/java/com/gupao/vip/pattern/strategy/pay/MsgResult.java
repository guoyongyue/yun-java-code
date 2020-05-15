package com.gupao.vip.pattern.strategy.pay;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class MsgResult {

    private int code;

    private String msg;

    private Object object;

    public MsgResult(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.object = object;
    }

    @Override
    public String toString() {
        return "支付状态：[" + code + "], " + msg  + ", 交易详情:" + object ;
    }
}
