package com.lxn.learn.netty.nio;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class NioTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(512);
        byteBuffer.flip();

        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        Arrays.asList(byteBuffers).stream().map(buffer -> "aa" + buffer.position());
    }
}
