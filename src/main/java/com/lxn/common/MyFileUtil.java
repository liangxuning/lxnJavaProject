package com.lxn.common;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

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



    private static String getHostIp() throws UnknownHostException {
        try {
            InetAddress candidateAddress = null;
            // 遍历所有的网络接口
            for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                // 在所有的接口下再遍历IP
//                iface.getName()
                for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
                        if (inetAddr.isSiteLocalAddress()) {
                            // 如果是site-local地址，就是它了
                            return inetAddr.getHostAddress();
                        } else if (candidateAddress == null) {
                            // site-local类型的地址未被发现，先记录候选地址
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            UnknownHostException unknownHostException = new UnknownHostException(
                    "Failed to determine LAN address: " + e);
            unknownHostException.initCause(e);
            throw unknownHostException;
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(getHostIp());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
