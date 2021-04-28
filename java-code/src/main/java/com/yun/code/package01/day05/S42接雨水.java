package com.yun.code.package01.day05;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class S42接雨水 {
    public static void main(String[] args) {

    }

    public int trap(int[] height) {

        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                max_left = max(max_left, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                max_right = max(max_right, height[j]);
            }
            ans += min(max_left, max_right) - height[i];
        }
        return ans;
    }

}
