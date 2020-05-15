package com.gupao.vip.decorator.passport.old;


import com.gupao.vip.decorator.passport.Member;
import com.gupao.vip.decorator.passport.ResultMsg;

/**
 * Created by qingbowu on 2019/3/19.
 */
public class SignService implements ISignService {

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultMsg register(String username, String password){
        return new ResultMsg(0,"注册成功",new Member(username,password));
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultMsg login(String username, String password){
        return new ResultMsg(0,"登陆成功",new Member(username,password));
    }
}
