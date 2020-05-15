package com.gupao.vip.decorator.passport.upgrade;

import com.gupao.vip.decorator.passport.ResultMsg;
import com.gupao.vip.decorator.passport.old.ISignService;

/**
 * Created by qingbowu on 2019/3/22.
 */
public interface ISigninForThirdService extends ISignService {

    /**
     * QQ登陆
     * @param openId
     * @return
     */
    ResultMsg loginForQQ(String openId);

    /**
     * 微信登陆
     * @param openId
     * @return
     */
    ResultMsg loginFoWeChat(String openId);



    /**
     * token登陆
     * @param token
     * @return
     */
    ResultMsg loginForToken(String token);

    /**
     * 手机号登陆
     * @param phone
     * @param coed
     * @return
     */
    ResultMsg loginForTelphone(String phone, String coed);


    /**
     * 登陆并注册
     * @param username
     * @param password
     * @return
     */
    ResultMsg loginForRegister(String username,String password);
}
