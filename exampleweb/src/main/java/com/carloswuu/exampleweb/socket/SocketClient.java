package com.carloswuu.exampleweb.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class SocketClient {
    public static void main(String[] args) {

        Socket client = new Socket();
        SocketAddress address = new InetSocketAddress(60666);
        try {
            client.connect(address,50000);
            client.getOutputStream().write("hello".getBytes());
            byte[] bytes = new byte[1024];
            client.getInputStream().read(bytes);
            System.out.println(new String(bytes));
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
