package com.yun.test;

public class A {
    public static void main(String[] args) {
        A a = new A();

        Thread thread = Thread.currentThread();
        thread.setName("2s321fdsf3sd1fwe");
        System.out.println("----------> thread name:"+thread.getName());
        a.printA();

    }

    public void printA() {
        System.out.println("----------->a");
        B b = new B();
        Thread thread = Thread.currentThread();
        System.out.println("----------> thread name:"+thread.getName());
        b.printB();
    }
}
class B {
    public void printB() {
        System.out.println("----------->b");
        C c = new C();
        Thread thread = Thread.currentThread();
        System.out.println("----------> thread name:"+thread.getName());
        c.printC();
    }
}
class C {
    public void printC() {
        Thread thread = Thread.currentThread();
        System.out.println("----------> thread name:"+thread.getName());
        System.out.println("----------->c");
    }
}