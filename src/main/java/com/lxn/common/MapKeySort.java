package com.lxn.common;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapKeySort {

        public static void main(String[] args) {

            Map<Integer, String> map = new HashMap<>();

//            map.put(4, "kfc");
//            map.put(3, "wnba");
//            map.put(2, "nba");
//            map.put(5, "cba");
            for (int i = 1 ; i< 600; i++) {
                map.put(i,String.valueOf(i*i));
            }
//            Map<Integer, String> resultMap = sortMapByKey(map);    //按Key进行排序

            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        }

        /**
         * 使用 Map按key进行排序
         * @param map
         * @return
         */
        public static Map<Integer, String> sortMapByKey(Map<Integer, String> map) {
            if (map == null || map.isEmpty()) {
                return null;
            }

            Map<Integer, String> sortMap = new TreeMap<Integer, String>(
                    new MapKeyComparator());

            sortMap.putAll(map);

            return sortMap;
        }
    }


class MapKeyComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer str1, Integer str2) {

        return str1.compareTo(str2);
    }
}
