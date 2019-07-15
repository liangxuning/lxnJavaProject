package util;

import java.io.IOException;

public class EncodeUtil {
    public static void main(String[] args) {
        String  ss = charsetConvert("梁旭宁", "gbk");
        System.out.println(ss);
    }
    private static String charsetConvert(String str, String charset) {
        try {
            str = new sun.misc.BASE64Encoder().encode(str.getBytes(charset));
            byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(str);
            str = new String(bytes, charset);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
