package com.gupao.vip.pattern.singlerton.lazy;

import java.lang.reflect.Constructor;

/**
 * Created by qingbowu on 2019/3/10.
 */
public class LazyInnerClassSingletonTest {

    public static void main(String[] args) {
        try {
            //调用者装B，不走寻常路，破坏了单例
            Class<?> cls = LazyInnerClassSingleton.class;
            Constructor<?> constructor = cls.getDeclaredConstructor(null);
            constructor.setAccessible(true);
            Object o1 = constructor.newInstance();


            //正常方式
            Object o2 = LazyInnerClassSingleton.getInstance();

            System.out.println(o1 == o2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
