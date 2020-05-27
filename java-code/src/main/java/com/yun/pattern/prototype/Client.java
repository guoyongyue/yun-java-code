package com.yun.pattern.prototype;

/**
 * @className: Client <br/>
 * @description: <br/>
 * @date: 2020/5/24 12:42 <br/>
 * @author: yun <br/>
 */
public class Client {

    /**
     * @methodName: <br/>
     * @params: <br/>
     * @description: <br/>
     * @date: 2020/5/24 12:42 <br/>
     * @author: yun <br/>
     */
    public Prototype startClone(Prototype concretePrototype) {
        return (Prototype) concretePrototype.clone();
    }
}
