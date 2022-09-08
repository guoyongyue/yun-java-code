package com.yun.code.package0700.package30;

import com.yun.util.InitUtil;
import com.yun.util.PrintUtil;

import java.util.Stack;

/**
 * 给定一个整数数组 temperatures ，表示每天的温度，
 * 返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
 * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class S739每日温度 {
    public static void main(String[] args) {
        S739每日温度 s739每日温度 = new S739每日温度();
        PrintUtil.print(s739每日温度.dailyTemperatures(InitUtil.initIntArray("73,74,75,71,69,72,76,73")));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] arr = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (stack != null && temperatures[i] > temperatures[stack.peek()]) {
                Integer pop = stack.pop();
                arr[pop] = i - pop;
            }
            stack.push(i);
        }
        return arr;
    }
}
