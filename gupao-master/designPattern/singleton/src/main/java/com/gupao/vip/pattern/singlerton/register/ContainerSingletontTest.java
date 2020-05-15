package com.gupao.vip.pattern.singlerton.register;

import com.gupao.vip.pattern.singlerton.concurrentExecutor.ConcurrentExecutor;

/**
 * Created by qingbowu on 2019/3/10.
 */
public class ContainerSingletontTest {

    public static void main(String[] args) {
        try {
            ConcurrentExecutor.execute(new ConcurrentExecutor.RunHandler(){
                @Override
                public void handler() {
                    Object bean = ContainerSingleton.getBean("com.gupao.vip.pattern.singlerton.register.ContainerSingleton");
                    System.out.println(System.currentTimeMillis()+":"+bean);
                }
            },10,6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
