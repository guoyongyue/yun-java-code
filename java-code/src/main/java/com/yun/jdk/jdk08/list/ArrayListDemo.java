package com.yun.jdk.jdk08.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

public class ArrayListDemo {
    private static List<String> traverseList = new LinkedList<String>() {{
        add("gzu ");
        add("pyu ");
        add("thinking ");
        add("in ");
        add("java ");
    }};

    /**
     * *********************************************
     * 以下为jdk8及以上的遍历方式
     */
    /**
     * 非常特殊的用法，与traverseList.iterator()方法类似，更强大;只有List借口特有用法
     */
    public void test9() {
        System.out.print("TraverseList method9:");
        ListIterator<String> listIterator = traverseList.listIterator();
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next());
        }
        System.out.println();
    }

    public void test8() {
        System.out.print("TraverseList method8:");
        traverseList.forEach(str -> {
            System.out.print(str);
        });
        System.out.println();
    }

    public void test7() {
        System.out.print("TraverseList method7:");
        traverseList.forEach(new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.print(str);
            }
        });
        System.out.println();
    }

    /**
     * 多线程并行执行
     */
    public void test6() {
        System.out.print("TraverseList method6:");
        traverseList.parallelStream().forEach(str -> {
            System.out.print(str);
        });
        System.out.println();
    }

    /**
     * 串行执行
     */
    public void test5() {
        System.out.print("TraverseList method5:");
        traverseList.stream().forEach(str -> {
            System.out.print(str);
        });
        System.out.println();
    }

    /**
     * *********************************************
     * 以下为java常规遍历List的方式
     * 1.优先选择foreach
     * 2.遍历List的同时要移除元素推荐使用iterator
     */
    public void test4() {
        System.out.print("TraverseList method4:");
        Iterator<String> iterator = traverseList.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
        System.out.println();
    }

    public void test3() {
        System.out.print("TraverseList method3:");
        for (Iterator<String> iterator = traverseList.iterator(); iterator.hasNext(); ) {
            System.out.print(iterator.next());
        }
        System.out.println();
    }

    public void test2() {
        System.out.print("TraverseList method2:");
        for (String str : traverseList) {
            System.out.print(str);
        }
        System.out.println();
    }

    public void test1() {
        System.out.print("TraverseList method1:");
        for (int i = 0; i < traverseList.size(); i++) {
            System.out.print(traverseList.get(i));
        }
        System.out.println();
    }
}
