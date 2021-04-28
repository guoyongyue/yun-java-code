package com.yun.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class PrintUtil {

    public static void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            print(matrix[i]);
            System.out.println();
        }
    }

    public static void print(int[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + " ");
        }
        System.out.println();
    }

    public static void print(String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            print(matrix[i]);
            System.out.println();
        }
    }

    public static void print(String[] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + " ");
        }
    }


    public static void print(List<Integer> list) {
        list.forEach(key -> System.out.println(key));
    }

    public static void print(LinkedList<Integer> list) {
        list.forEach(key -> System.out.println(key));
    }

    public static void print(String value){
        System.out.println("------>"+value+" ret");
    }

    public static void print(int value){
        System.out.println("------>"+value);
    }

    public static void print(Map<String, String> map) {
        map.forEach((key, value) -> System.out.printf("key : %s; value : %s; thread: %s\n", key, value, Thread.currentThread().getName()));
    }


    public static void printlnBegin(String name){
        System.out.println();
        System.out.println("------>"+name+" begin");
    }

    public static void printEnd(String name){
        System.out.println("------>"+name+" end");
        System.out.println();
    }
}
