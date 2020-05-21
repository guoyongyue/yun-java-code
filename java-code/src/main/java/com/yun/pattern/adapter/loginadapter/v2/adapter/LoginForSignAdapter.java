package com.yun.pattern.adapter.loginadapter.v2.adapter;

import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  LoginForSignAdapter <br/>
 * @description: <br/>
 * @date: 2020/5/21 23:27<br/>
 * @author: yun<br />
 */
public class LoginForSignAdapter implements LoginAdapter {

    /**
     * @param adapter
     * @method name: isSupport  <br/>
     * @description:
     * @date: 2020/5/21 23:27<br/>
     * @author: yun
     */
    @Override
    public boolean isSupport(Object adapter) {
        return adapter instanceof LoginForSignAdapter;
    }

    /**
     * @param key adapter
     * @method name: login <br/>
     * @description:
     * @date: 2020/5/21 23:27<br/>
     * @author: yun
     */
    @Override
    public ResultMsg login(String key, Object adapter) {
        return null;
    }
}
