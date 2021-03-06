package com.lxn.common;

import com.jcraft.jsch.*;
import com.lxn.common.sshutil.MyUserInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinuxCmdUtil {

    public static void main(String[] args) {
        List<String> cmds = new ArrayList<String>();
        cmds.add("sudo passwd gframe");
        cmds.add("gframe");
        cmds.add("gframe");
        System.out.println("success: " + executeCommand("10.12.3.178", "gframe", "admin", cmds));
    }

    private static String executeCommand(String host, String user, String password, List<String> cmds) {
        JSch jsch = new JSch();
        String result = "";

        //	Channel channel = null;
        Session session = null;
        ChannelShell channelShell=null;
        try {
            session = jsch.getSession(user, host, 22);
            session.setPassword(password);
            session.setServerAliveCountMax(0);
            UserInfo ui = new MyUserInfo() {
                public void showMessage(String message) {
                    System.out.println(message);
                }

                public boolean promptYesNo(String message) {
                    return true;
                }
            };

            session.setUserInfo(ui);

            session.connect(30000); // making a connection with timeout.

            /*
             * channel = session.openChannel("shell"); String cmdsString = ""; for (String
             * cmd : cmds) { cmdsString += cmd+" \r\n"; } System.out.println(cmdsString);
             * InputStream is = new ByteArrayInputStream(cmdsString.getBytes());
             * channel.setInputStream(is); ByteArrayOutputStream baos = new
             * ByteArrayOutputStream(); channel.setOutputStream(baos);
             * System.out.println("channel1:" + channel.isConnected());
             * channel.connect(30000); TimeUnit.SECONDS.sleep(5); String tmpResult = new
             * String(baos.toByteArray()); // channel.connect(30000); // result+=tmpResult;
             * System.out.println(tmpResult);
             *
             */

            channelShell = (ChannelShell) session.openChannel("shell");
            channelShell.connect(3000);
            InputStream inputStream = channelShell.getInputStream();
            OutputStream outputStream = channelShell.getOutputStream();
            for (String cmd : cmds) {
                outputStream.write((cmd + "\n\r").getBytes());
                outputStream.flush();
                TimeUnit.SECONDS.sleep(1);
                // BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
                System.out.println("发送指令：  " + cmd);
                byte[] tmp = new byte[1024];
                while (true) {
                    while (inputStream.available() > 0) {
                        int i = inputStream.read(tmp, 0, 1024);
                        if (i < 0)
                            break;
                        System.out.println("返回#####" + new String(tmp, 0, i) + "#####");
                        String s = new String(tmp, 0, i);
                        if (s.equals("\r") || s.equals("\n") || s.equals("\r\n")) {
                            outputStream.write((password + "\n\r").getBytes());
                            System.out.println("遇到\\r发送旧密码：  " + password);
                            outputStream.flush();
                            TimeUnit.SECONDS.sleep(2);
                        }
                        if (s.indexOf("New password") != -1 || s.indexOf("新的") != -1) {
                            break;
                        }
                        if (s.indexOf("[sudo]") != -1) {
                            outputStream.write((password + "\n\r").getBytes());
                            System.out.println("发送旧密码：  " + password);
                            outputStream.flush();
                            TimeUnit.SECONDS.sleep(2);
                        }
                        result = new String(tmp, 0, i);
                    }
                    TimeUnit.SECONDS.sleep(1);
                    if (channelShell.isClosed()) {
                        if (inputStream.available() > 0)
                            continue;
                        System.out.println("exit-status: " + channelShell.getExitStatus());
                        break;
                    }
                    break;
                    // inputStream.close();
                }
            }

        } catch (JSchException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channelShell != null)
                channelShell.disconnect();
            if(session!=null)
                session.disconnect();
        }
        return result;
    }
}
