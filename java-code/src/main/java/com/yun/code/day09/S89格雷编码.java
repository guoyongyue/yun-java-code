package com.yun.code.day09;

import com.yun.util.ArrayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 *
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。即使有多个不同答案，你也只需要返回其中一种。
 *
 * 格雷编码序列必须以 0 开头。
 *
 */
public class S89格雷编码 {
    public static void main(String[] args) {
        S89格雷编码 s89格雷编码 = new S89格雷编码();
        List<Integer> integers = s89格雷编码.grayCode(2);
        ArrayUtil.print(integers);

    }
    public List<Integer> grayCode(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int num=1;
        for (int i = 0; i < n; i++) {
            num=num*2;
        }
        for (int i = 0; i < num; i++) {
            res.add(i);
        }

//        ArrayList<Integer> res = new ArrayList<Integer>();
//        res.add(0);
//        int head = 1;
//        for (int i = 0; i < n; i++) {
//            for (int j = res.size() - 1; j >= 0; j--)
//                res.add(head + res.get(j));
//            head <<= 1;
//        }
        return res;
    }
}
