package com.yun.pattern.adapter.loginadapter.v1.service;

import com.yun.pattern.adapter.loginadapter.ResultMsg;

public class SignForThirdService extends SignService {

    public ResultMsg loginForQQ(String openId) {
        return loginForRegister(openId, null);
    }

    public ResultMsg loginForRegister(String username, String password) {
        super.register(username, password);
        return super.login(username, password);
    }

    public ResultMsg loginForWeChat(String openId) {
        return null;
    }


    public ResultMsg loginForToken(String token) {
        return null;
    }

    public ResultMsg loginForTelPhone(String phone, String code) {
        return null;
    }


}
