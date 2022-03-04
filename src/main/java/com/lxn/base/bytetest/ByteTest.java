package com.lxn.base.bytetest;

import com.lxn.common.BytesUtil;
import com.lxn.common.EncodeUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.jboss.netty.buffer.ChannelBuffers.dynamicBuffer;

public class ByteTest {


    @Test
    /**
     * 回车转换
     */
    public void byteTest1() {
        String s = "\r\n";
        String ss = "\\r\\n";
        System.out.println(s.equals(ss));

        byte[] b = s.getBytes();
        System.out.println(Arrays.toString(b));

        String sss = StringEscapeUtils.unescapeJava(ss);
        System.out.println(Arrays.toString(sss.getBytes()));
    }

    @Test
    public void byteTest2() {
        byte[] b = "l梁旭".getBytes();
        String s = BytesUtil.printHexString(b);
        System.out.println(s);
        BytesUtil.convertHexStr2ByteArr(s);
    }

    /**
     * 测试utf-8转gbk编码{@link com.lxn.common.EncodeUtil}
     */
    @Test
    public void byteTest3() throws UnsupportedEncodingException {
        String s = "s梁宁";
        System.out.println(BytesUtil.printHexString(s.getBytes("gbk")));
        ChannelBuffer channelBuffer = dynamicBuffer(0);
        channelBuffer.writeBytes(s.getBytes("gbk"));
        EncodeUtil.turn(channelBuffer);
EncodeUtil.encodetest(s);
    }
}
