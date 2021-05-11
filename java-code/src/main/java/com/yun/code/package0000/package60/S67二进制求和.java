package com.yun.code.package0000.package60;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 */
public class S67二进制求和 {
    public static void main(String[] args) {
        S67二进制求和 s67 = new S67二进制求和();
        String s = s67.addBinary("1111", "1111");
        System.out.println(s);
    }

    public String addBinary(String a, String b) {
        StringBuilder sum = new StringBuilder("");
        int a_length = a.length();
        int b_length = b.length();
        if (a_length < b_length) {
            String temp = a;
            a = b;
            b = temp;
        }
        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0; i--, j--) {
            int value = 0;
            if (j < 0) {
                value = Integer.valueOf(a.substring(i, i + 1)) + 0 + carry;
            } else {
                value = Integer.valueOf(a.substring(i, i + 1)) + Integer.valueOf(b.substring(j, j + 1)) + carry;
            }

            if (value == 2) {
                value = 0;
                carry = 1;
            } else if (value == 3) {
                value = 1;
                carry = 1;
            } else {
                carry = 0;
            }
            sum.insert(0, value);

        }
        if (carry != 0) {
            sum.insert(0, carry);
        }

        return sum.toString();
    }
}
