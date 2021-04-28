package com.yun.code.package01.day07;

import com.yun.util.InitUtil;
import com.yun.util.PrintUtil;

/**
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class S66加一 {
    public static void main(String[] args) {
        S66加一 s66 = new S66加一();
        int[] a = new int[3];
        a[0]=9;
        a[1]=9;
        a[2]=9;
        PrintUtil.print(a);//9 9 9
        PrintUtil.print(a);//1 0 0 0

        int[] ints = s66.plusOne(a);
    }

    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        PrintUtil.print(digits);
        System.out.println();
        digits[0] = 1;
        PrintUtil.print(digits);
        return digits;
    }
}
