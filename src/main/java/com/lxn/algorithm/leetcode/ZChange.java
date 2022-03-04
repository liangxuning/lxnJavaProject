package com.lxn.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ZChange {
    public static void main(String[] args) {
        String s = "AB";
        convert(s, 1);


    }

    public static String convert(String s, int numRows) {
        int len = (int) Math.ceil((float)s.length() / (numRows + numRows - 2)) * (numRows -1);
//        int len = (s.length() / (numRows + numRows - 2))*(numRows -1);
        if (len == 0) {
            len = s.length();
        }
        System.out.println(len);
        int charat = 0;
        int[][] ints = new int[numRows][len];
        int outinsert = numRows - 1;
        if (numRows == 1) {
            outinsert = 1;
        }
        int i = 0;
        int j = 0;
        for (j = 0;j< len;j++) {
            int tempout = j % outinsert;
            if (tempout == 0) {
                for (i = 0; i< numRows;i++) {
                    if (charat < s.length()) {
                        ints[i][j] = s.charAt(charat++);
                    }
                }
            } else {
                if (charat < s.length()) {
                    ints[numRows - 1 - tempout][j] = s.charAt(charat++);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (i = 0;i< numRows;i++) {
            for (j = 0;j< len ;j++) {
                if (ints[i][j]!= 0) {
                    sb.append(Character.valueOf((char) ints[i][j]));
                }
            }
        }
        System.out.println(sb.toString());

        return sb.toString();
    }


        public String convert1(String s, int numRows) {

            if (numRows == 1) return s;

            List<StringBuilder> rows = new ArrayList<>();
            for (int i = 0; i < Math.min(numRows, s.length()); i++)
                rows.add(new StringBuilder());

            int curRow = 0;
            boolean goingDown = false;

            for (char c : s.toCharArray()) {
                rows.get(curRow).append(c);
                if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
                curRow += goingDown ? 1 : -1;
            }

            StringBuilder ret = new StringBuilder();
            for (StringBuilder row : rows) ret.append(row);
            return ret.toString();
        }


}
