package com.yun.pattern.proxy.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class BuyHouseProxyTest {
    public static void main(String[] args) {
        try {
            Intermediary intermediary = new Intermediary();
            BuyHouse customer =(BuyHouse) intermediary.getInstance(new Customer());
            customer.buyHouse();


            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{BuyHouse.class});
            FileOutputStream fos = new FileOutputStream("$Proxy0.class");
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}