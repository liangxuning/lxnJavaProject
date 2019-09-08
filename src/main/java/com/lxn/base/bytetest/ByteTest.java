package com.lxn.base.bytetest;

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
}
