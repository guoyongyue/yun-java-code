package com.gupao.vip.decorator.passport.old;


import com.gupao.vip.decorator.passport.ResultMsg;

/**
 * Created by qingbowu on 2019/3/19.
 */
public class SigninForThirdService extends SignService {

    public ResultMsg loginForQQ(String openId){
        //1、openId是全局唯一，我们可以把它当做一个用户名(加长)
        //2、密码默认为QQ_EMPTY
        //3、注册(在原有系统里创建一个用户)
        //4、调用原来的登录方法
        return loginForRegister(openId,null);
    }

    /**
     * 微信登陆
     * @param openId
     * @return
     */
    public ResultMsg loginForWeChat(String openId){
        return null;
    }


    /**
     * token登陆
     * @param token
     * @return
     */
    public ResultMsg loginFortoken(String token ){
        //通过token拿到用户信息然后登陆
        return null;
    }

    /**
     * 手机号登陆
     * @param phone
     * @param code
     * @return
     */
    public ResultMsg loginForTelphone(String phone,String code ){
        return null;
    }


    /**
     * 注册并登陆
     * @param username
     * @param password
     * @return
     */
    public ResultMsg loginForRegister(String username, String password){
        super.register(username,password);
        return super.login(username,password);
    }
}
