package com.lxn.algorithm;

import java.util.HashMap;
import java.util.Map;

public class StringLen {
    public static void main(String[] args) {
        String s = "asdqweaqwdszxcsgsd";
        int[] m = new int[256];
        Map<String, Integer> map = new HashMap<>();
        int left = 0;
        int len = 0;
        map.put("len", len);
        for (int i = 0;i<s.length();i++) {
            left = Math.max(left, m[s.charAt(i)]);
            len = Math.max(len, i - left + 1);
            m[s.charAt(i)] = i+1;
            if (len > map.get("len")) {
                map.put("len", len);
                map.put("left", left);
            }
        }

        System.out.println(left +  "---" + len);
        System.out.println(s.substring(map.get("left"), map.get("left") + map.get("len")));
    }
}
