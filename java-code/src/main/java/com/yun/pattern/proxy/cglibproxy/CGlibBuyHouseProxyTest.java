/*
package com.yun.pattern.proxy.cglibproxy;

import net.sf.cglib.core.DebuggingClassWriter;

public class CGlibBuyHouseProxyTest {

    public static void main(String[] args) {
        try {
            //JDK是采用读取接口的信息
            //CGlib是采用覆盖父类方法
            //目的：都是生成一个新的类，去实现增强代码逻辑的功能

            //JDK Proxy 对于用户而言，必须要有一个接口实现，目标类相对来说复杂
            //CGlib 可以代理任意一个普通的类，没有任何要求

            //CGlib 生成代理逻辑更复杂，但是效率方面，调用效率更高，生成一个包含了所有逻辑的FastClass，不需要再反射。
            //JDK Proxy 生成代理的逻辑简单，执行效率相对要低，因为每次调用都要反射

            //CGlib 有个坑， 不能代理final修饰的方法

            // 生成class文件到指定目录
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"d://cglib_proxy_classes");
            Customer  customer = (Customer)new CGlibIntermediary().getInstance(Customer.class);
            customer.buyHouse();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
}
*/
