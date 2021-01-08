package com.sven.netty.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @date ：Created in 2020/12/21 9:13
 * @description：
 * @version:
 * @see
 */
public class TimeServer2 {
    public static void main(String[] args)  {
        int port = 8080;
        ServerSocket ss =  null;

        try {
            ss = new ServerSocket();
            System.out.println("the time server is start in port : "+port);
            TimeServer2Handler timeServer2Handler = new TimeServer2Handler(50,10000);
            Socket socket = null;
            while (true){
                socket = ss.accept();
                //由直接创建线程模式换成线程池模式
                timeServer2Handler.execute(new TimeServerHandler(socket));
            }
        }
        catch (Exception ex){

        }
        finally {
            if(ss!=null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ss = null;
            }
        }
    }
}
