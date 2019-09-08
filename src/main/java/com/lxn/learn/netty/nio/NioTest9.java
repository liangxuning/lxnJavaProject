package com.lxn.learn.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest9 {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest8.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();
        channel.lock(3, 6, true);
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(0, (byte)'a');
        map.put(1, (byte)'b');
        randomAccessFile.close();
    }
}
