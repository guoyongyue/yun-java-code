package com.yun.test;

public class IntegerTest {
    public static void main(String[] args) {
        Integer i1=10;
        Integer i2=10;
        System.out.println(i1==i2);

        Integer i3=129;
        Integer i4=129;
        System.out.println(i3==i4);

        int i5=12;
        int i6=12;
        System.out.println(i5==i6);

        int i7=new Integer(10);
        int i8=new Integer(10);
        System.out.println(i7==i8);

        Integer i9=new Integer(10);
        Integer i10=new Integer(10);
        System.out.println(i9==i10);
    }
}
