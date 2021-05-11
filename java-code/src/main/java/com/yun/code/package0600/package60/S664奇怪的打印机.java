package com.yun.code.package0600.package60;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 *
 * 打印机每次只能打印同一个字符序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给定一个只包含小写英文字母的字符串，你的任务是计算这个打印机打印它需要的最少次数。
 *
 */
public class S664奇怪的打印机 {
    public static void main(String[] args) {

    }

    int[][] memo;
    public int strangePrinter(String s) {
        int N = s.length();
        memo = new int[N][N];
        return dp(s, 0, N - 1);
    }
    public int dp(String s, int i, int j) {
        if (i > j){
            return 0;
        }
        if (memo[i][j] == 0) {
            int ans = dp(s, i+1, j) + 1;
            for (int k = i+1; k <= j; ++k){
                if (s.charAt(k) == s.charAt(i)){
                    ans = Math.min(ans, dp(s, i, k-1) + dp(s, k+1, j));
                }
            }
            memo[i][j] = ans;
        }
        return memo[i][j];
    }

}
