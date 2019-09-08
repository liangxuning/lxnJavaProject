package com.lxn.algorithm;

public class DynamicTest {
    public static void main(String[] args) {

    }
    //p 利润，n 长度
    public int steel(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int qmax = 0;
        for (int i = 0; i< n;i++) {
            qmax = Math.max(qmax, p[i] + steel(p, n - i));
        }
        return qmax;
    }
    //备忘录自顶向下
    public int steel2(int[] p, int n, int[] note) {
        if (note[n] >=0) {
            return note[n];
        }
        if (n == 0) {
            return 0;
        }
        int qmax = 0;
        for (int i = 0; i< n;i++) {
            qmax = Math.max(qmax, p[i] + steel2(p, n - i,  note));
        }
        note[n] = qmax;
        return qmax;
    }

    //自底向上
    public int steel3(int[] p, int n) {
        int[] r = new int[n];
        r[0] = 0;
        for (int i = 1;i< n;i++) {
            int q = -1;
            for (int j = 1;j< i;j++) {
                q =Math.max(q, p[j] + r[i - j]);
            }
            r[i] = q;
        }
        return r[0];
    }
}
