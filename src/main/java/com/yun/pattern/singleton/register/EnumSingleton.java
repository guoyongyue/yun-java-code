package com.yun.pattern.singleton.register;

/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/5/12 8:53<\br>
*/
public enum EnumSingleton {

    INSTANCE;

    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
