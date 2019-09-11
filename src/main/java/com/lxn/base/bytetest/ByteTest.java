package com.lxn.base.bytetest;

import com.lxn.common.BytesUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
}
