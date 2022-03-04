package com.lxn.test;

import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class TestClass {

    private static String oidTable = ".1.3.6.1.2.1.25.2.3.1";
    private static String oneOid = ".1.3.6.1.2.1.25.2.3.1.3";
    private static int index= 2;
    public static void main1(String[] args) {
        String extendOid = "";
        for (int j = 1;j<=index;j++) {
            extendOid += ".0";
        }
        String index = findIndex(oneOid + extendOid).trim();
        String column = findColumn(index, oneOid + extendOid).trim();
        System.out.println(index);
        System.out.println(column);
    }

    private static String findIndex(String value){
        StringBuilder sb = new StringBuilder();
        String[] arr = value.split("\\.");
        int indexNum = index;
        for(int i = indexNum; i >= 1; i --){
            sb.append(arr[arr.length - i]);
            if(i != 1){
                sb.append(".");
            }
        }
        return sb.toString();
    }

    private static String findColumn(String index, String value){

        int indexIndex = value.lastIndexOf(index);
        int oidTableBeginIndex = value.indexOf(oidTable);

        return value.substring(oidTableBeginIndex + oidTable.length() + 1, indexIndex - 1);
    }


    public static void main2(String[] args) {
//        String sb = "LST-PORT::DEV=10.52.42.254,FN=0,SN=2,PN=10:CTAG::SHOWOPTION=NAMEALIAS PSPEED PDIRECT LOOPSTAT TESTSTAT FEC RN AState;\\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=28:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=28:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=34:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=34:CTAG::;\nLST-PORT::DEV=10.52.42.254,FN=0,SN=2,PN=9:CTAG::SHOWOPTION=NAMEALIAS PSPEED PDIRECT LOOPSTAT TESTSTAT FEC RN AState;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=9,ONTID=2:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=9,ONTID=2:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=5:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=5:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=8:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=8:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=12:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=12:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=37:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=37:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=20:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=20:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=23:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=23:CTAG::;\nLST-ONTRUNINFO::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=0:CTAG::;\nLST-ONTDDMDETAIL::DEV=10.52.42.254,FN=0,SN=2,PN=10,ONTID=0:CTAG::;\n";
        String sb = "display  current-configuration  interface  Tunnel";
        LinkedList<String> command = strToList(sb);
        String expr = "";
int splitNum = command.size();
        int remainder = command.size() % splitNum; // (先计算出余数)
        int number = command.size() / splitNum; // 然后是商
        int offset = 0;//偏移量
        for (int i = 0; i < splitNum; i++) {
            List<String> value = null;
            if (remainder > 0) {
                value = (LinkedList<String>) command.subList(i * number + offset, (i + 1) * number + offset + 1);
                remainder--;
                offset++;
            } else {
                value =  command.subList(i * number + offset, (i + 1) * number + offset);
            }
            System.out.println(value);
        }

//        for(int id =1;(expr = command.pollFirst()) != null;id++){
//            if(expr.trim().length() == 0){
//                continue;
//            }
////            System.out.println(expr.trim());
//
////            cmdlist.add(cmdlist.size(), cmd);
//        }
//        System.out.println(command);

    }


    public static LinkedList<String> strToList(String str){

//        if(StringUtil.isBlank(str)){
//            return null;
//        }
        LinkedList<String> linkedlist = new LinkedList<String>();

        StringReader sr = null;
        LineNumberReader lnr = null;
        try {
            sr = new StringReader(str);
            lnr = new LineNumberReader(sr);
            String line = null;
            for(;(line = lnr.readLine()) != null;){
                if(line.trim().length() == 0){
                    continue;
                }
                linkedlist.offerLast(line.trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(lnr != null){
                    lnr.close();
                }
            } catch (Exception e2) {
            }finally{
                try {
                    if(sr != null){
                        sr.close();
                    }
                } catch (Exception e3) {
                }
            }
        }
        return linkedlist;
    }


    public static void main(String[] args) {
        Integer i = Integer.MAX_VALUE;
        System.out.println(i);
    }
}
