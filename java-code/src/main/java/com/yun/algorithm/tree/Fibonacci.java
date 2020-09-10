package com.yun.algorithm.tree;

import com.yun.util.StringUtil;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/9/10 9:27<\br>
*/
public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        StringUtil.printlnBegin("迭代打印斐波那契数列");
        StringUtil.printlnRet(fibonacci.fib(5));
        StringUtil.printlnEnd("迭代打印斐波那契数列");

        StringUtil.printlnBegin("迭代打印斐波那契数列 剪枝法");
        StringUtil.printlnRet(fibonacci.fib2(5));
        StringUtil.printlnEnd("迭代打印斐波那契数列 剪枝法");



        StringUtil.printlnBegin("迭代打印斐波那契数列 ⾃底向上");
        StringUtil.printlnRet(fibonacci.fib3(5));
        StringUtil.printlnEnd("迭代打印斐波那契数列 ⾃底向上");
    }

    private int fib(int n){
        if(n==1 || n==2){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }


    private int fib2(int n){
        Vector<Integer> memo = new Vector<>(16);
        memo.add(1);
        memo.add(1);
        return helper(memo, n);
    }

    private int helper(Vector<Integer> memo,int n){
        if(n==1 || n==2){
            return 1;
        }
        if(memo.size()<n){
            int i = helper(memo, n - 1) + helper(memo, n - 2);
            memo.add(i);
            return i;
        }else {
            return memo.get(n-1);
        }
    }


    private int fib3(int n){
        if(n==1 || n==2){
            return 1;
        }

        Vector<Integer> memo = new Vector<>(16);
        memo.add(1);
        memo.add(1);
        for (int i = 3; i <= n; i++) {
            memo.add(memo.get(i-2)+memo.get(i-3));
        }
        return memo.get(n-1);
    }


}
