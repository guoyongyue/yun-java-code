package com.gupao.vip.pattern.singlerton.register;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

/**
 * Created by qingbowu on 2019/3/10.
 */
public class EnumSingletonTest {


    public static void main(String[] args) {
        //序列化的方式并不能破坏枚举单例
        EnumSingleton o1 = EnumSingleton.getInstance();
        o1.setObj(new Object());
        EnumSingleton o2 = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o1);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            o2 =(EnumSingleton)ois.readObject();
            ois.close();

            System.out.println(o1);
            System.out.println(o2);
            System.out.println(o1 == o2);
            System.out.println(o1.getObj() == o2.getObj());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //*******************************************************************************
        try {
            //这种方式会抛java.lang.IllegalArgumentException: Cannot reflectively create enum objects异常
            //因为在JDK层面就为枚举不能被反射和序列化破坏来保驾护航
            Class cls = EnumSingleton.class;
            Constructor constructor = cls.getDeclaredConstructor(String.class, int.class);
            constructor.setAccessible(true);
            EnumSingleton enumSingleton = (EnumSingleton)constructor.newInstance("abc",666);

            System.out.println(enumSingleton);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
