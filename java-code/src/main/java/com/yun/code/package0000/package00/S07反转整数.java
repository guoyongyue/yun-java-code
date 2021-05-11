package com.yun.code.package0000.package00;

/**
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 */
class S07反转整数 {
    public static int reverse(int x) {
        StringBuilder sb = new StringBuilder(String.valueOf(x));
        StringBuilder temp = new StringBuilder();
        if(sb.charAt(0)=='+' || sb.charAt(0)=='-'){
            temp.append(sb.substring(1,sb.length()));
            temp.reverse();
            temp.insert(0,sb.charAt(0));
        }else {
            temp=sb.reverse();
        }
        int num =0;
        try {
            Integer.valueOf(temp.toString());
        }catch (Exception e){
            return 0;
        }
        return Integer.valueOf(temp.toString());
    }
}