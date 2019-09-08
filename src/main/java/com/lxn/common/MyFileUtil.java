package com.lxn.common;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class MyFileUtil {
    public void getResourceFile() {
        FileSystemResource fileSystemResource = new FileSystemResource("/Users/liangxuning/梁旭宁.log");
        try {

            String filename = URLEncoder.encode(fileSystemResource.getFilename(), StandardCharsets.UTF_8.toString());
            System.out.println(filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(fileSystemResource.getFilename());
        try {
            System.out.println(fileSystemResource.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
