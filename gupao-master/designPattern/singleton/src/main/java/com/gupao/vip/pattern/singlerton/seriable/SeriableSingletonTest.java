package com.gupao.vip.pattern.singlerton.seriable;

import java.io.*;

/**
 * Created by qingbowu on 2019/3/10.
 */
public class SeriableSingletonTest {

    public static void main(String[] args) {
        SeriableSingleton o1 = SeriableSingleton.getInstance();
        SeriableSingleton o2 = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o1);
            oos.flush();
            oos.close();

            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            o2 =(SeriableSingleton)ois.readObject();
            ois.close();

            System.out.println(o1);
            System.out.println(o2);
            System.out.println(o1 == o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
