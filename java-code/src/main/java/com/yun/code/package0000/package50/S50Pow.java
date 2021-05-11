package com.yun.code.package0000.package50;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 */
public class S50Pow {
    public static void main(String[] args) {

    }
    public double myPow(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        for (long i = 0; i < N; i++)
            ans = ans * x;
        return ans;
    }

}
