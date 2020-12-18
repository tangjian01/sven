package com.sven.netty.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ：jian.tang@dmall.com
 * @date ：Created in 2020/12/18 9:09
 * @description：
 * @version: 1.0
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println(" the time server is start in port :" + port);
            Socket socket = null;
            while (true){
                System.out.println("before accept");
                socket = server.accept();  //监听客户端的链接
                System.out.println("after accept, current socket is :" + socket.getRemoteSocketAddress());
                new Thread(new TimeServerHandler(socket)).start(); //一个连接下的处理器
            }
        } catch (Exception ex) {
        } finally {

        }
    }
}
