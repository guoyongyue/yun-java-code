package com.yun.code.util;

public class StringUtil {
    //掩码姓名
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

    //掩码手机号
    public static String maskPhone(String tel) {
        if (tel == null || tel.length() < 11) {
            return null;
        }
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return tel;
    }
}
