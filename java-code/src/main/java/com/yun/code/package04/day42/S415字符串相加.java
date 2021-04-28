package com.yun.code.package04.day42;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 *  
 * <p>
 * 提示：
 * <p>
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 */
public class S415字符串相加 {
    public static void main(String[] args) {
        System.out.println(new S415字符串相加().addStrings("9", "99"));
    }

    public String addStrings(String num1, String num2) {
        int tempInt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length(), j = num2.length(); i > 0 || j > 0; i--, j--) {
            int numInt1 = i > 0 ? num1.charAt(i - 1) - '0' : 0;
            int numInt2 = j > 0 ? num2.charAt(j - 1) - '0' : 0;
            int value = (numInt1 + numInt2 + tempInt) % 10;
            tempInt = (numInt1 + numInt2 + tempInt) / 10;
            sb.append(value);
        }
        if (tempInt > 0) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }
}
