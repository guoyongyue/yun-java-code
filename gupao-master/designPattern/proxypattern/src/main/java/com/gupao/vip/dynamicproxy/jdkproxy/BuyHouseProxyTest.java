package com.gupao.vip.dynamicproxy.jdkproxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

/**
 * Created by qingbowu on 2019/3/11.
 */
public class BuyHouseProxyTest {

    public static void main(String[] args) {
        try {
            Customer customer = new Customer();
            Intermediary intermediary = new Intermediary();

            BuyHouse customerProxy = (BuyHouse)intermediary.getInstance(customer);
            System.out.println(customerProxy.getClass());
            customerProxy.buyHouse();


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
