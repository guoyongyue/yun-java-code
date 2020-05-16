package com.yun.pattern.proxy.myproxy;

public class BuyHouseMyProxyTest {

    public static void main(String[] args) {
        try {
            Customer customer = new Customer();
            MyIntermediary intermediary = new MyIntermediary();

            BuyHouse customerProxy = (BuyHouse)intermediary.getInstance(customer);
            System.out.println(customerProxy.getClass());
            customerProxy.buyHouse();


//            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{BuyHouse.class});
//            FileOutputStream fos = new FileOutputStream("$Proxy0.class");
//            fos.write(bytes);
//            fos.flush();
//            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
