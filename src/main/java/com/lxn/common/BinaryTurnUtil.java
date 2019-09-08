package com.lxn.common;

import java.util.Scanner;

public class BinaryTurnUtil {
    public static void main(String[] args) {
        String s = "     \n 梁旭宁啦啦啦、n\n lalala啦啦";
        String utf2uni = utf8ToUnicode(s);
        System.out.println(utf2uni);
        String uni2gbk = unicodeToGbk(utf2uni);
        System.out.println(uni2gbk);
    }
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
     * utf-8转unicode
     * @param inStr
     * @return
     */
    public static String utf8ToUnicode(String inStr) {
        char[] myBuffer = inStr.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < inStr.length(); i++) {
            Character.UnicodeBlock ub = Character.UnicodeBlock.of(myBuffer[i]);
            if (ub == Character.UnicodeBlock.BASIC_LATIN) {
                sb.append(myBuffer[i]);
            } else if (ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                int j = (int) myBuffer[i] - 65248;
                sb.append((char) j);
            } else {
                short s = (short) myBuffer[i];
                String hexS = Integer.toHexString(s);
                String unicode = "\\u" + hexS;
                sb.append(unicode.toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * unicode转utf-8
     * @param theString
     * @return
     */
    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
    /**
     * gbk转unicode
     * @param str
     * @return
     */
    public static String gbkToUnicode(String str) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char chr1 = (char) str.charAt(i);
            if ((chr1 & (0x00FF)) == chr1) {
                result.append(chr1);
                continue;
            }
            result.append("\\u" + Integer.toHexString((int) chr1));
        }
        return result.toString();
    }

    /**
     * unicode转gbk
     * @param dataStr
     * @return
     */
    public static String unicodeToGbk(String dataStr) {
        int index = 0;
        StringBuffer buffer = new StringBuffer();
        int li_len = dataStr.length();
        while (index < li_len) {
            if (index >= li_len - 1
                    || !"\\u".equals(dataStr.substring(index, index + 2))) {
                buffer.append(dataStr.charAt(index));
                index++;
                continue;
            }
            String charStr = "";
            charStr = dataStr.substring(index + 2, index + 6);
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(letter);
            index += 6;
        }
        return buffer.toString();
    }
}
