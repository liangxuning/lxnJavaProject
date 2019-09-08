package com.lxn.algorithm;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int[] is =  {3, 4, 2, 7, 5, 1};
        int temp = 0;
        for (int i = 1; i <is.length;i++) {
            temp = is[i];
            while (i >=1 && temp < is[i - 1]) {
                is[i] = is[i - 1];
                i--;
            }
            is[i] = temp;
        }

        System.out.println(Arrays.toString(is));
    }
}
