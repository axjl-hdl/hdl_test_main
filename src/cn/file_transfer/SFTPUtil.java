package cn.file_transfer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.jcraft.jsch.*;

//jsch-0.1.53.jar
public class SFTPUtil {
	
	//文件路径前缀,即文件在Linux服务器的目录路径
    private static final String PRE_FIX = "/hudl";
    
	private static final Map<String, Channel> SFTP_CHANNEL_POOL = new HashMap<String, Channel>();
	 
	/** 
     * 获取sftp协议连接. 
     * @param host 主机名 
     * @param port 端口 
     * @param username 用户名 
     * @param password 密码 
     * @return 连接对象 
     * @throws JSchException 异常 
     */  
    public static ChannelSftp getSftpConnect(final String host, final int port, final String username,  
            final String password) throws JSchException { 
        Session sshSession = null;  
        Channel channel = null;  
        ChannelSftp sftp = null;  
        String key = host + "," + port + "," + username + "," + password;  
        if (SFTP_CHANNEL_POOL.get(key)==null) {  
            JSch jsch = new JSch();  
            jsch.getSession(username, host, port);  
            sshSession = jsch.getSession(username, host, port);  
            sshSession.setPassword(password);  
            Properties sshConfig = new Properties();  
            sshConfig.put("StrictHostKeyChecking", "no");  
            sshSession.setConfig(sshConfig);  
            sshSession.connect();  
            channel = sshSession.openChannel("sftp");  
            channel.connect();  
            SFTP_CHANNEL_POOL.put(key, channel);  
        } else {  
            channel = SFTP_CHANNEL_POOL.get(key);  
            sshSession = channel.getSession();  
            if (!sshSession.isConnected())  
                sshSession.connect();  
            if (!channel.isConnected())  
                channel.connect();  
        }  
        sftp = (ChannelSftp) channel;  
        return sftp;  
    }  
    
    //处理文件名
    public static List<String> formatPath(final String filePath) {  
        List<String> list = new ArrayList<String>(2);  
        String repSrc = filePath.replaceAll("\\\\", "/");  
        int firstP = repSrc.indexOf("/");  
        int lastP = repSrc.lastIndexOf("/");  
        String fileName = lastP + 1 == repSrc.length() ? "" : repSrc.substring(lastP + 1);  
        String dir = firstP == -1 ? "" : repSrc.substring(firstP, lastP);  
        dir = PRE_FIX + (dir.length() == 1 ? dir : (dir + "/"));  
        list.add(dir);  
        list.add(fileName);  
        return list;  
    }  
    
	public static void main(String[] args) {
		
		String line = null;
		try {
			ChannelSftp sftp = getSftpConnect("192.168.65.227", 22, "root", "Sjf!@#789");  
			List<String> list = formatPath("GD_20170320_gd.txt");  
			StringBuilder builder = new StringBuilder();
			BufferedReader br =new BufferedReader(new InputStreamReader(sftp.get(list.get(0) + list.get(1))));
			while ((line = br.readLine())!=null) {
				builder.append(line);
				builder.append("\r\n");// \r回车 \n换行
			}
			
			System.out.println(builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
