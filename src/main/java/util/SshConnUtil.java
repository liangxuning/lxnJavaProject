package util;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SshConnUtil {
	private static InputStream is = null;
	private static InputStreamReader isr = null;
	private static BufferedReader input = null;
	private static ChannelShell channelShell=null;
	//连接到远程主机获得session
	public static Session getConnection(String ip, String port, String username, String password) {
		JSch jsch = new JSch();
        MyUserInfo userInfo = new MyUserInfo();
      //创建session并且打开连接，因为创建session之后要主动打开连接
        Session session = null;
		try {
			session = jsch.getSession(username, ip, Integer.parseInt(port));
			session.setPassword(password);
			session.setUserInfo(userInfo);
			session.setTimeout(31536000);
			session.connect();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			if (session != null) {
				SshConnUtil.close(session);
			}
			e.printStackTrace();
		}
		return session;
	}
	//执行方法
	public static BufferedReader executeCmd(Session session, final String command, String logEncoding) {
            //打开通道，设置通道类型，和执行的命令
        Channel channel;
		try {
			channel = session.openChannel("exec");
			ChannelExec channelExec = (ChannelExec)channel;
			channelExec.setCommand(command);
			channelExec.setInputStream(null);

			is = channelExec.getInputStream();
			isr = new InputStreamReader(is,logEncoding);
			input = new BufferedReader(isr);
			channelExec.connect();
			System.out.println("The remote command is :" + command);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return input;
    }
	public static BufferedReader executesudoCmd(Session session, final String command, String logEncoding) throws IOException {

		try {
			channelShell = (ChannelShell) session.openChannel("shell");
			channelShell.connect(3000);

			is = channelShell.getInputStream();
			isr = new InputStreamReader(is,logEncoding);
			input = new BufferedReader(isr);
		} catch (JSchException e) {
			e.printStackTrace();
		}

		try (OutputStream outputStream = channelShell.getOutputStream();){
			outputStream.write("sudo cat /home/godu/test.txt\r".getBytes());
			outputStream.flush();
			TimeUnit.SECONDS.sleep(1);
			outputStream.write("godu\r".getBytes());
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return input;
	}
    public static ChannelExec getChannelExec(Session session) {
		Channel channel;
		try {
			channel = session.openChannel("exec");
			return (ChannelExec)channel;
		} catch (JSchException e) {
			e.printStackTrace();
		}
		return null;
	}
	//关闭远程连接
	public static void close(Session session) {
		int returnCode = 0;
		try {
			isr.close();
			is.close();
			channelShell.disconnect();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Channel channel;
		try {
			channel = session.openChannel("exec");
			ChannelExec channelExec = (ChannelExec)channel;
			// 得到returnCode
			if (channelExec.isClosed()) {  
				returnCode = channelExec.getExitStatus();  
			}  
			
			// 关闭通道
			channelExec.disconnect();
			//关闭session
			session.disconnect();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				System.out.println(cmd);
				byte[] tmp = new byte[1024];
				while (true) {
					while (inputStream.available() > 0) {
						int i = inputStream.read(tmp, 0, 1024);
						if (i < 0)
							break;
						System.out.println(new String(tmp, 0, i));
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
