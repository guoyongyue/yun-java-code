package com.yun.pattern.delegate.simple;

public class Test {
    /**
     * 全权代理 委派模式
     * @param args
     */
    public static void main(String[] args) {
        Boss boss = new Boss();
        boss.common("加密",new Leader());
        boss.common("架构",new Leader());
    }
}
