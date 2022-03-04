package com.lxn.test;

public class Testss {
    public static void main(String[] args) {
        Double aDouble = 30000d;
        int i = 1;
        for (i =30;i>0;i--) {
            aDouble +=aDouble*0.1;
            System.out.println(i+ ":" + aDouble);
        }
    }
}
