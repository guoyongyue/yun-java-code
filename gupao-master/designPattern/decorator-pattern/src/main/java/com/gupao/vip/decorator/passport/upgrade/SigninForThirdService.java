package com.gupao.vip.decorator.passport.upgrade;

import com.gupao.vip.decorator.passport.ResultMsg;
import com.gupao.vip.decorator.passport.old.ISignService;

/**
 * Created by qingbowu on 2019/3/22.
 */
public class SigninForThirdService implements ISigninForThirdService  {

    private ISignService iSignService;

    public SigninForThirdService(ISignService iSignService) {
        this.iSignService = iSignService;
    }

    @Override
    public ResultMsg register(String username, String password) {
        return this.iSignService.register(username,password);
    }

    @Override
    public ResultMsg login(String username, String password) {
        return this.login(username,password);
    }

    @Override
    public ResultMsg loginForQQ(String openId) {
        return null;
    }

    @Override
    public ResultMsg loginFoWeChat(String openId) {
        return null;
    }

    @Override
    public ResultMsg loginForToken(String token) {
        return null;
    }

    @Override
    public ResultMsg loginForTelphone(String phone, String coed) {
        return null;
    }

    @Override
    public ResultMsg loginForRegister(String username, String password) {
        return null;
    }


}
