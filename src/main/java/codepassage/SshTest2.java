package codepassage;

import com.jcraft.jsch.Session;
import util.SshConnUtil;

import java.io.*;

public class SshTest2 {
    public static void main(String[] args) throws IOException {
        Session session = SshConnUtil.getConnection("10.31.2.35", "22", "godu", "godu");
//        BufferedReader br = SshConnUtil.executeCmd(session, "sudo cat /var/log/xferlog", "utf-8");
//        BufferedReader br = SshConnUtil.executeCmd(session, "cat /data/gframe/网元.txt", "utf-8");
        BufferedReader br = SshConnUtil.executesudoCmd(session, "sudo cat /home/godu/test.txt", "utf-8");
//        BufferedReader br = SshConnUtil.executesudoCmd(session, "sudo cat /var/log/xferlog", "utf-8");
        String s = null;
        while (!(s = br.readLine()).equals("")) {
            System.out.println(s);
        }
        System.out.println("wanbi");
        SshConnUtil.close(session);
        System.out.println("jieshu");
    }


}
