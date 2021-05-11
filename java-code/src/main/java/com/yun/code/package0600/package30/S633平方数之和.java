package com.yun.code.package0600.package30;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 */
public class S633平方数之和 {
    public static void main(String[] args) {
        boolean b = judgeSquareSum(1000000000);
        System.out.println(1000000000 + " " + b);
        b = judgeSquareSum(3);
        System.out.println(3 + " " + b);
        b = judgeSquareSum(4);
        System.out.println(4 + " " + b);
        b = judgeSquareSum(5);
        System.out.println(5 + " " + b);
        b = judgeSquareSum(6);
        System.out.println(6 + " " + b);
        b = judgeSquareSum(7);
        System.out.println(7 + " " + b);
        b = judgeSquareSum(8);
        System.out.println(8 + " " + b);
        b = judgeSquareSum(9);
        System.out.println(9 + " " + b);
    }

    public static boolean judgeSquareSum(int c) {
        int left = 0;
        int right = (int) Math.sqrt(c);
        while (left <= right) {
            if ((left * left + right * right) == c) {
                return true;
            } else if ((left * left + right * right) < c) {
                left++;
            } else if ((left * left + right * right) > c) {
                right--;
            }
        }
        return false;
    }
}
