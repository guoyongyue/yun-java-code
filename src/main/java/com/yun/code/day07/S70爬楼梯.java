package com.yun.code.day07;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class S70爬楼梯 {
    public static void main(String[] args) {
        int i = climbStairs(2);
        System.out.println(i);
    }
    public static int climbStairs(int n) {
        int memo[] = new int[n+1];
         climb_Stairs(n,memo);
        return memo[n];
    }

    public static int climb_Stairs(int i,  int memo[]) {
        if(i==1){
            memo[1]=1;
        }
        if(i==2){
            memo[2]=2;
        }
        if(memo[i]>0){
            return memo[i];
        }
        return memo[i]=climb_Stairs(i-1,memo)+climb_Stairs(i-2,memo);
    }

}
