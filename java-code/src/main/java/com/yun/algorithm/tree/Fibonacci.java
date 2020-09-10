package com.yun.algorithm.tree;

import com.yun.util.StringUtil;

/**
 * @author: yun<\br>
 * @description: <\br>
 * @date:  2020/9/10 9:27<\br>
*/
public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        StringUtil.printlnBegin("迭代打印斐波那契数列");
        StringUtil.printlnRet(fibonacci.fib(3));
        StringUtil.printlnEnd("迭代打印斐波那契数列");

    }

    private int fib(int n){
        if(n==1 || n==2){
            return 1;
        }
        return fib(n-1)+fib(n-2);
    }
}
