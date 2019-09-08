package com.lxn.test;

import java.io.File;
import java.net.URI;

public class URItest {
    public static void main(String[] args) {
        File file = new File("/Users/liangxuning/Private/git_project/lxnJavaProject/src/main/resources/music/read.txt");

        System.out.println(file.toURI().toString());
    }
}
