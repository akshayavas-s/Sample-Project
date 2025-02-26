package com.exampleapi;

public class B {
    public static void main(String[] args) {
        B a1 = new B();
        int x =a1.test();
        System.out.println(x);
    }

    public int test() {
        System.out.println(500);
        System.out.println(300);
        return 100;
    }
}
