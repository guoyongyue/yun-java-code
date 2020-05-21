package com.yun.pattern.adapter.loginadapter.v2.adapter;

import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  LoginForQQAdapter <br/>
 * @description: <br/>
 * @date: 2020/5/21 23:22<br/>
 * @author: yun<br />
 */
public class LoginForQQAdapter implements LoginAdapter {

    /**
     * @param adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:22<br/>
     * @author: yun
     */
    @Override
    public boolean isSupport(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    /**
     * @param key adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:22<br/>
     * @author: yun
     */
    @Override
    public ResultMsg login(String key, Object adapter) {
        return null;
    }
}