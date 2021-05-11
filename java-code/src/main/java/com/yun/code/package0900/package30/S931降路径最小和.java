package com.yun.code.package0900.package30;

import com.yun.util.InitUtil;

/**
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * <p>
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * <p>
 *  
 */
public class S931降路径最小和 {
    public static void main(String[] args) {
        int[][] ints = InitUtil.initIntArray("2,1,3", "6,5,4", "7,8,9");
        int i = minFallingPathSum(ints);
        System.out.println(i);
    }

    public static int minFallingPathSum(int[][] matrix) {
        int ret=Integer.MAX_VALUE;
        if(matrix.length==1){
            for (int j = 0; j < matrix[0].length; j++) {
                ret=Math.min(ret,matrix[0][j]);
            }
            return ret;
        }

        for (int j = 1; j < matrix.length; j++) {
            for (int i = 0; i < matrix[0].length; i++) {
                int a =0;
                if(i==0){
                    a = matrix[j - 1][i];
                }else {
                    a = matrix[j - 1][i - 1];
                }
                int b = matrix[j - 1][i];
                int c =0;
                if(i==matrix[0].length-1){
                    c = matrix[j - 1][i];
                }else {
                    c = matrix[j - 1][i + 1];
                }
                int min1 = Math.min(a, b);
                int min2 = Math.min(b, c);
                int min = Math.min(min1, min2);
                matrix[j][i]=min+matrix[j][i];
                if(j==matrix.length-1){
                    ret=Math.min(ret,matrix[j][i]);
                }
            }
        }
        return ret;
    }
}
