package com.yun.pattern.adapter.loginadapter.v2;

import com.yun.pattern.adapter.loginadapter.ResultMsg;

/**
 * @class name:  IPassportForThird <br/>
 * @description: <br/>
 * @date: 2020/5/21 22:44<br/>
 * @author: yun<br />
 */
public interface IPassportForThird {

    /**
     * @param openId
     * @method name:  loginForQQ <br/>
     * @description:
     * @date: 2020/5/21 22:45<br/>
     * @author: yun
     */
    ResultMsg loginForQQ(String openId);

    /**
     * @param openId
     * @method name:  loginFoWeChat <br/>
     * @description:
     * @date: 2020/5/21 22:45<br/>
     * @author: yun
     */
    ResultMsg loginFoWeChat(String openId);

    /**
     * @param token
     * @method name:  loginForToken <br/>
     * @description:
     * @date: 2020/5/21 22:45<br/>
     * @author: yun
     */
    ResultMsg loginForToken(String token);


    /**
     * @param phone coed
     * @method name:  loginForTelPhone <br/>
     * @description:
     * @date: 2020/5/21 22:45<br/>
     * @author: yun
     */
    ResultMsg loginForTelPhone(String phone, String coed);

    /**
     * @param username password
     * @method name:  loginForRegister <br/>
     * @description:
     * @date: 2020/5/21 22:46<br/>
     * @author: yun
     */
    ResultMsg loginForRegister(String username, String password);
}