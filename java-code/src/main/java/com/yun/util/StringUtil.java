package com.yun.util;

/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/9/10 9:30<\br>
*/
public class StringUtil {
    public static String maskName(String name) {
        if (name == null || name.length() < 2) {
            return null;
        }
        if (name.length() == 2) {
            name = name.substring(0, 1) + "*";
        } else {
            name = name.replaceAll(name.substring(1, name.length() - 1), "*");
        }
        return name;
    }


    public static String maskPhone(String tel) {
        if (tel == null || tel.length() < 11) {
            return null;
        }
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return tel;
    }

    public static void printlnBegin(String name){
        System.out.println();
        System.out.println("------>"+name+" begin");
    }

    public static void printlnRet(String value){
        System.out.println("------>"+value+" ret");
    }

    public static void printlnRet(int value){
        System.out.println("------>"+value);
    }

    public static void printlnEnd(String name){
        System.out.println("------>"+name+" end");
        System.out.println();
    }

}
