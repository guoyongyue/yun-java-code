package com.yun.pattern.adapter.loginadapter.v2;

import com.yun.pattern.adapter.loginadapter.Member;
import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  SignService <br/>
 * @description: <br/>
 * @date: 2020/5/21 22:42<br/>
 * @author: yun<br />
 */
public class SignService {

    /**
     * @param username password
     * @method name:  register <br/>
     * @description:
     * @date: 2020/5/21 22:42<br/>
     * @author: yun
     */
    public ResultMsg register(String username, String password) {
        return new ResultMsg(0, "注册成功", new Member(username, password));
    }

    /**
     * @param username username
     * @method name: login  <br/>
     * @description:
     * @date: 2020/5/21 22:43<br/>
     * @author: yun
     */
    public ResultMsg login(String username, String password) {
        return new ResultMsg(0, "登陆成功", new Member(username, password));
    }
}
