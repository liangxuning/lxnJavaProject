package leetcode;


import java.util.HashMap;
import java.util.Map;


public class TwoSum {
    public static void main(String[] args) {
        int[] sum = {1, 2, 3, 4};
        int target = 5;
//        for (int i = 0; i < sum.length - 1; i++) {
//            for (int j = i + 1; j < sum.length; j++) {
//                if (sum[i] + sum[j] == target) {
//                    System.out.println("[" + i + ", " + j + "]");
//                }
//            }
//        }

        //o(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sum.length; i++) {
            Integer integer = map.get(target - sum[i]);
            if (integer == null) {
                map.put(sum[i], i);
            } else {
                System.out.println("[" + i + ", " + integer + "]");
            }
        }

    }
}
