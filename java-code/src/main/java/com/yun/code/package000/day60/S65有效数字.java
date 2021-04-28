package com.yun.code.package000.day60;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 *
 * 例如:
 *
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 *
 */
public class S65有效数字 {
    public static void main(String[] args) {
        S65有效数字 s65有效数字 = new S65有效数字();
        s65有效数字.isNumber("1e3");
    }
    public boolean isNumber(String s) {
        if(s.indexOf("f")>0){
            return false;
        }
        if(s.indexOf("D")>0){
            return false;
        }
        try {
            Float num = Float.valueOf(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
