package com.yun.code.day07;

import com.yun.code.util.ArrayUtil;

/**
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 */
public class S64最小路径和 {
    public static void main(String[] args) {
        S64最小路径和 s64不同路径 = new S64最小路径和();
        int[][] temp = new int[3][3];
        temp[0][0]=1;temp[0][1]=3;temp[0][2]=1;
        temp[1][0]=1;temp[1][1]=5;temp[1][2]=1;
        temp[2][0]=4;temp[2][1]=2;temp[2][2]=1;
        int i = s64不同路径.minPathSum(temp);
        System.out.println("result:"+i);
    }
    public int minPathSum(int[][] grid) {

        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }

        for (int i = 1; i < grid.length; i++) {
            grid[i][0] = grid[i][0] + grid[i - 1][0];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] = (grid[i-1][j]>grid[i][j-1]) ? (grid[i][j-1]+grid[i][j]):(grid[i-1][j]+grid[i][j]);

            }
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }
}
