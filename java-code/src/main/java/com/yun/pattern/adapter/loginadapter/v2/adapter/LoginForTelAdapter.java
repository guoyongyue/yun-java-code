package com.yun.pattern.adapter.loginadapter.v2.adapter;

import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  LoginForTelAdapter <br/>
 * @description: <br/>
 * @date: 2020/5/21 23:28<br/>
 * @author: yun<br />
 */
public class LoginForTelAdapter implements LoginAdapter {
    /**
     * @param adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:28<br/>
     * @author: yun
     */
    @Override
    public boolean isSupport(Object adapter) {
        return adapter instanceof LoginForTelAdapter;
    }

    /**
     * @param key adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:29<br/>
     * @author: yun
     */
    @Override
    public ResultMsg login(String key, Object adapter) {
        return null;
    }
}
