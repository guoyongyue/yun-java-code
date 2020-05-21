package com.yun.pattern.adapter.loginadapter.v2.adapter;

import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  LoginAdapter <br/>
 * @description: <br/>
 * @date: 2020/5/21 23:21<br/>
 * @author: yun<br />
 */
public interface LoginAdapter {

    /**
     * @param adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:21<br/>
     * @author: yun
     */
    boolean isSupport(Object adapter);

    /**
     * @param key adapter
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:22<br/>
     * @author: yun
     */
    ResultMsg login(String key, Object adapter);
}