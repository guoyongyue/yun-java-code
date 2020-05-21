package com.yun.pattern.adapter.loginadapter.v2.adapter;

import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  LoginForWeChatAdapter <br/>
 * @description: <br/>
 * @date: 2020/5/21 23:30<br/>
 * @author: yun<br />
 */
public class LoginForWeChatAdapter implements LoginAdapter {
    /**
     * @param adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:30<br/>
     * @author: yun
     */
    @Override
    public boolean isSupport(Object adapter) {
        return adapter instanceof LoginForWeChatAdapter;
    }

    /**
     * @param key adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:31<br/>
     * @author: yun
     */
    @Override
    public ResultMsg login(String key, Object adapter) {
        return null;
    }
}
