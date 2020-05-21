package com.yun.pattern.adapter.loginadapter.v2;

import com.yun.pattern.adapter.loginadapter.ResultMsg;
import com.yun.pattern.adapter.loginadapter.v2.adapter.*;

/**
 * @class name:  PassportForThirdAdapter <br/>
 * @description: <br/>
 * @date: 2020/5/21 23:20<br/>
 * @author: yun<br />
 */
public class PassportForThirdAdapter extends SignService implements IPassportForThird {

    /**
     * @param openId
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:23<br/>
     * @author: yun
     */
    @Override
    public ResultMsg loginForQQ(String openId) {
        return this.processLogin(openId, LoginForQQAdapter.class);
    }

    /**
     * @param openId
     * @method name:  loginFoWeChat <br/>
     * @description:
     * @date: 2020/5/21 23:23<br/>
     * @author: yun
     */
    @Override
    public ResultMsg loginFoWeChat(String openId) {
        return this.processLogin(openId, LoginForWeChatAdapter.class);
    }

    /**
     * @param token
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:23<br/>
     * @author: yun
     */
    @Override
    public ResultMsg loginForToken(String token) {
        return this.processLogin(token, LoginForTokenAdapter.class);
    }

    /**
     * @param phone coed
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:23<br/>
     * @author: yun
     */
    @Override
    public ResultMsg loginForTelPhone(String phone, String coed) {
        return this.processLogin(phone, LoginForTelAdapter.class);
    }

    /**
     * @param username password
     * @method name:   <br/>
     * @description:
     * @date: 2020/5/21 23:24<br/>
     * @author: yun
     */
    @Override
    public ResultMsg loginForRegister(String username, String password) {
        super.register(username,password);
        return super.login(username,password);
    }


    /**
     * @param key clazz
     * @method name:   <br/>
     * @description: 工厂模式  策略模式
     * @date: 2020/5/21 23:32<br/>
     * @author: yun
     */
    private ResultMsg processLogin(String key, Class<? extends LoginAdapter> clazz) {
        try {
            LoginAdapter loginAdapter = clazz.newInstance();
            if (loginAdapter.isSupport(loginAdapter)) {
                return loginAdapter.login(key, loginAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
