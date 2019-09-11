package com.lxn.common;

import java.util.Scanner;

public class BytesUtil {

    /**
     * 16进制转10进制
     * @param args
     */
    public void binary2ten(String[] args){
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String x = sc.next();
            char[] chars = x.toCharArray();
            int num = 0;
            for(int i=chars.length -1;i>=0;i--){
                int tmp =chars[i];
                if (tmp >= 'A') {
                    tmp = tmp - 'A' + 10;
                } else {
                    tmp = chars[i] - '0';
                }
                System.out.println(tmp);
                num += tmp*Math.pow(16,chars.length-i-1);
            }
            System.out.println(num);
        }
        sc.close();
    }

    /**
     * 把16进制字符串（长度为2）转换为含有一个字节的字节数组
     */
    public static byte[] convertHexStr2ByteArr(String hexStr) {
        String numberStr = hexStr.substring(2); // 去掉0x
        int theInt = Integer.parseInt(numberStr, 16);
        return new byte[] { (byte) theInt };
//        F1 1111 0001 & 1100 0000 = 1100 0000 1000 0000
    }

    /**
     * 将字节数组转换为16进制字符串
     */
    public static String printHexString(byte[] hexStr) {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < hexStr.length; i++) {
            String hex = Integer.toHexString(hexStr[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret.append(hex.toUpperCase() + " ");
        }
        return ret.toString();
    }
}
