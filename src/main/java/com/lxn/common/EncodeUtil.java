package com.lxn.common;

import org.jboss.netty.buffer.ChannelBuffer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import static org.jboss.netty.buffer.ChannelBuffers.dynamicBuffer;

public class EncodeUtil {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String  ss = charsetConvert("梁旭宁", "utf-8");
        System.out.println(ss);

        String s = "s梁旭宁sli梁";
        ChannelBuffer channelBuffer = dynamicBuffer(0);
        channelBuffer.writeBytes(s.getBytes("utf-8"));
        System.out.println(new String(channelBuffer.toString(Charset.forName("utf-8")).getBytes(Charset.forName("gbk")), "gbk"));
//        turn(channelBuffer);
        turn(channelBuffer);

    }
    private static String charsetConvert(String str, String charset) {
        try {
            str = new sun.misc.BASE64Encoder().encode(str.getBytes(charset));
            byte[] bytes = new sun.misc.BASE64Decoder().decodeBuffer(str);
            str = new String(bytes, "gbk");
        } catch(IOException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static void turn(ChannelBuffer bytes) {
        byte[] bini = new byte[bytes.readableBytes()];
        byte tempByte;
        int lastIndex = bytes.readableBytes() -1;
        int sufixCodeNum = 0;
        for(int i = lastIndex ; i >= 0 ; i--){
            tempByte = bytes.getByte(i);
            //单字符
            if((tempByte >=0 && tempByte <= 0x7F)
                    ||tempByte == 0xFF || tempByte == 0xFE)break;
            //utf-8编码的后缀
            if((tempByte & 0xC0) == 0x80){
                sufixCodeNum++;
                continue;
            }
            lastIndex = i;
            //utf-8编码的开头
            if(Integer.toBinaryString(tempByte).indexOf('0')-24 == sufixCodeNum+1){
                lastIndex = bytes.readableBytes() -1;
            }
            break;
        }
        if(sufixCodeNum==0||lastIndex != bytes.readableBytes() -1){
            if(bytes.readableBytes()>0){
                bytes = bytes.slice(0, bytes.readableBytes() - (sufixCodeNum + 1));
            }
        }
        bini = bytes.toString(Charset.forName("utf-8")).getBytes(Charset.forName("gbk"));
        System.out.println(new String(bini, Charset.forName("gbk")));
    }

        public static void turn2() {
            try {
                String s = URLEncoder.encode("s梁宁", "gbk");
                System.out.println(s);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        public static void encodetest(String s) {
            try {
                byte[] bytes = s.getBytes("gbk");
                System.out.println(BytesUtil.printHexString(bytes));
                System.out.println(new String(bytes,"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

}
