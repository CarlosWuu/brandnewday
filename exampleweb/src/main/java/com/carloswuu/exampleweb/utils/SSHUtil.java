package com.carloswuu.exampleweb.utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.InputStream;

public class SSHUtil {
    private String ip;
    private String username;
    private String password;
    private final static int PORT = 22;

    public SSHUtil(String ip,String username,String password){
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    public void execute(String command){
        JSch jsch = new JSch();

        try{
            Session session = jsch.getSession(username, ip, PORT);
            session.setPassword(password);
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();


            ChannelExec channel = (ChannelExec)session.openChannel("exec");

            channel.setCommand(command);
            channel.setInputStream(null);
            channel.setErrStream(System.err);

            InputStream in=channel.getInputStream();
            channel.connect();

            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
                if(channel.isClosed()){
                    if(in.available()>0) continue;
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {
        SSHUtil ssh = new SSHUtil("10.135.66.103","appuser","MaxusTest2020_");

        ssh.execute("cd /app \n ls");
    }

}
