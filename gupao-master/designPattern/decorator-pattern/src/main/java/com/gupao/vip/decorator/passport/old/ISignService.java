package com.gupao.vip.decorator.passport.old;

import com.gupao.vip.decorator.passport.Member;
import com.gupao.vip.decorator.passport.ResultMsg;

/**
 * Created by qingbowu on 2019/3/22.
 */
public interface ISignService  {

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    public ResultMsg register(String username, String password);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    public ResultMsg login(String username,String password);
}
