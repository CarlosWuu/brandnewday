package com.carloswuu.exampleweb.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class SocketsServer {
    private ServerSocket serverSocket;

    public SocketsServer(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void start(){
        try {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();


            while(true){
                byte[] bytes = new byte[1024];

                int read = inputStream.read(bytes);

                String msg = new String(bytes,0,read);
                outputStream.write(bytes);

                if(msg.equals("hello")){
                    serverSocket.close();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(66666);
            SocketsServer ss = new SocketsServer(serverSocket);

            ss.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
