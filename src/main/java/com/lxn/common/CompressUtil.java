package com.lxn.common;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CompressUtil {
    public static void main(String[] args) {
        String fileString = "/Users/liangxuning/test/xxxxxxxxxx.zip";
        String destpath = "/Users/liangxuning/test/ssss/";
        File file = new File(fileString);
        try {
            deCompressLocal(file, destpath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static List<String> deCompressLocal(File file, String destpath) throws IOException {
        List list = new ArrayList();
        byte[] b = new byte[1024];
        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ZipInputStream zipis = new ZipInputStream(bis);
        ZipEntry zipe = null;
        BufferedOutputStream bos = null;
        try {
            while ((zipe = zipis.getNextEntry()) != null) {
                bos = null;
                File f = new File(destpath + zipe.getName());
                System.out.println(f.getAbsolutePath());
                if (zipe.isDirectory()) {
                    f.mkdirs();
                } else {
                    int l;
                    File parent = f.getParentFile();
                    String ff = parent.getAbsolutePath() + "/bbb/" + f.getName();
                    File parents = new File(parent.getAbsolutePath() + "/bbb/");
                    if (!(parents.exists())) {
                        parents.mkdirs();
                    }

                    FileOutputStream fos = new FileOutputStream(ff);
                    bos = new BufferedOutputStream(fos);

                    while ((l = zipis.read(b)) != -1) {
                        bos.write(b, 0, l);
                    }
                    bos.flush();
                    bos.close();
                }
                zipis.closeEntry();
                list.add(f.getAbsolutePath());
            }
            zipis.close();
            return list;
        } finally {
            if (zipis != null) {
                zipis.close();
            }
            if (bos != null)
                bos.close();
        }
    }
}
