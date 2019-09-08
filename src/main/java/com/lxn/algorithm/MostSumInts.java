package com.lxn.algorithm;

public class MostSumInts {
    public static void main(String[] args) {
        int[] is = {3, -33, 2, 32, -55, 12, -34, 45, 65, -3, 24, 27, 85, -23, 89, 3, -95, -3, -67};
        int start = 0;
        int end = 0;
        int sumData = 0;
        int max = 0;
        for (int i = 0;i< is.length - 1;i++) {
            sumData = is[i];
            for (int j = i + 1; j < is.length - i;j++) {
                sumData += is[j];
                if (sumData > max) {
                    start = i;
                    end = j;
                    max = sumData;
                }
            }
        }
        System.out.println(start + "-" + end + "--" + max);
    }
}
