package com.lxn.common;

import org.jboss.netty.buffer.ChannelBuffer;

import java.io.IOException;
import java.nio.charset.Charset;

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
    public static void turn(ChannelBuffer bytes) {
//					logger.info(chBufini.readableBytes() + "------" + chBuf.readableBytes());
//					logger.info("utf-8默认要转码为GBK");
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
}
