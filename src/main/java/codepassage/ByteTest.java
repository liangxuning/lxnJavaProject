package codepassage;

import com.boco.godu.util.HexBinaryConverter;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.Arrays;

public class ByteTest {
    public static void main(String[] args) {
        String s = "\r\n";
        String ss = "\\r\\n";
        if (s.equals(ss)) {
            System.out.println("ok");
        }
        byte[] b = s.getBytes();
        System.out.println(Arrays.toString(b));
        String sss = StringEscapeUtils.unescapeJava(ss);
        String ssss = sss.trim();
        System.out.println(Arrays.toString(sss.getBytes()));
        String s1 = "";
        System.out.println(Arrays.toString(convert2NECommond(s1)));
    }

    private static byte[] convert2NECommond(String script){
        byte[] buf = new byte[script.length()];
        if(script == null || ("".equals(script.trim()) && !script.equals("\r")
                && !script.equals("\n") && !script.equals("\r\n"))) return buf;
        if(script.toUpperCase().startsWith("0X") && 4 == script.trim().length()){
            System.out.println("发送单个字符为[" + script.trim() + "]");
            buf = HexBinaryConverter.convertHexStr2ByteArr(script.trim().toUpperCase());
        }else{
            System.out.println("发送指令[" + script + "]");
            buf = script.getBytes();
        }
        return buf;
    }
//    public static byte[] transCtrlByte(byte[] bArray){
//        byte[] buf = new byte[bArray.length];
//        if(bArray != null && bArray.length > 0){
//            boolean preIsAngleBracket = false;
//            for(byte b: bArray){
//                if(preIsAngleBracket){
//                    if(b == (byte)33) {//ascii: 33 (!)，represent ^.
//                        buf.w((byte)94);
//                    }else if(b >= 64 && b <= 95){
//                        buf.writeByte(b - 64);
//                    }else if(b >=97 && b <= 122){
//                        buf.writeByte(b - 96);
//                    }else{
//                        buf.writeByte((byte)94);
//                        buf.writeByte(b);
//                    }
//                    preIsAngleBracket = false;
//                    continue;
//                }
//                if(b == (byte)94){
//                    preIsAngleBracket = true;
//                }else{
//                    preIsAngleBracket = false;
//                    buf.writeByte(b);
//                }
//            }
//        }
//        return buf;
//    }
}
