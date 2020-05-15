package com.gupao.vip.pattern.adapter.loginadapter.v1;

import com.gupao.vip.pattern.adapter.loginadapter.v1.service.SigninForThirdService;

/**
 * Created by qingbowu on 2019/3/19.
 */
public class SigninForThirdServiceTest {

    public static void main(String[] args) {

        SigninForThirdService thirdService = new SigninForThirdService();
        thirdService.login("zhangsan","123456");
        thirdService.loginForQQ("455456156651");
        thirdService.loginForTelphone("15172456789","4513896");
        thirdService.loginFortoken("ngjasfjkashfaafks");

    }
}
