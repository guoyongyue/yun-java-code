package com.yun.code.package0900.package80;

/**
 * 给定两个整数 A 和 B，返回任意字符串 S，要求满足：
 * <p>
 * S 的长度为 A + B，且正好包含 A 个 'a' 字母与 B 个 'b' 字母；
 * 子串 'aaa' 没有出现在 S 中；
 * 子串 'bbb' 没有出现在 S 中。
 *  
 * aabaa
 * aabaabaa
 */
public class S984不含AAA或BBB的字符串 {
    public static void main(String[] args) {
        String s = strWithout3a3b(3, 1);
        System.out.println(s);
        s = strWithout3a3b(4, 1);
        System.out.println(s);
        s = strWithout3a3b(1, 4);
        System.out.println(s);
    }

    public static String strWithout3a3b(int a, int b) {
        StringBuilder sb =new StringBuilder();
        while (a>0 && b>0){
            if(a>b){
                sb.append("aab");
                a--;
                a--;
                b--;
            }else if(a<b){
                sb.append("bba");
                a--;
                b--;
                b--;
            }else {
                sb.append("ab");
                a--;
                b--;
            }
        }
        if(a==1){
            sb.append("a");
        }
        if(a==2){
            sb.append("aa");
        }
        if(b==1){
            sb.append("b");
        }
        if(b==2){
            sb.append("bb");
        }
        return sb.toString();

    }
}
