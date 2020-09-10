package com.yun.code.day08;

import com.yun.util.ArrayUtil;

/**
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 * <p>
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 */
public class S74搜索二维矩阵 {
    public static void main(String[] args) {
        int[][] ints2 = ArrayUtil.initArray("1,2,3,4", "5,6,7,8", "9,10,11,12", "13,14,15,16");
        ArrayUtil.print(ints2);
        System.out.println(S74搜索二维矩阵.searchMatrix(ints2, 10));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) {
            return false;
        }
        int n = matrix[0].length;

        // 二分查找
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) {
                return true;
            } else {
                if (target < pivotElement) {
                    right = pivotIdx - 1;
                } else {
                    left = pivotIdx + 1;
                }
            }
        }
        return false;
    }
}
