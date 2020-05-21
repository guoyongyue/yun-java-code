package com.yun.pattern.adapter.loginadapter.v1.service;

import com.yun.pattern.adapter.loginadapter.Member;
import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  SignService <br/>
 * @description: <br/>
 * @date:  2020/5/21 22:17<br/>
 * @author: yun<br/>
*/
public class SignService {

    public ResultMsg register(String username, String password){
        return new ResultMsg(0,"注册成功",new Member(username,password));
    }

    public ResultMsg login(String username,String password){
        return new ResultMsg(0,"登陆成功",new Member(username,password));
    }

}
