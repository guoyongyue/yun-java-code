package com.yun.code.package0000.package60;

/**
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 问总共有多少条不同的路径？
 *
 *
 */
public class S62不同路径 {

    public int uniquePaths(int m, int n) {
        int[][] dq=new int[m][n];
        for (int i=0;i<n;i++){
            dq[0][i]=1;
        }
        for (int i=0;i<m;i++){
            dq[i][0]=1;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                dq[i][j]= dq[i-1][j]+dq[i][j-1];
            }
        }
        return dq[m-1][n-1];

    }
}
