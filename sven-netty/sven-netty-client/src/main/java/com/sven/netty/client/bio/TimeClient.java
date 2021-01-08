package com.sven.netty.client.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * date: 2020/12/13
 * version: 2.0
 */
public class TimeClient {

    public static void main(String[] args) {
        for (int connect = 0; connect <= 10; connect++) {
            new Thread( ()->{
                doConnect();
            } ).start();
        }
    }

    public static void doConnect(){
        int port = 8080;
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        String ip = "127.0.0.1";

        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            //同一个socket下的链接
            Double d = Math.random();
            out.println(" query time order :" + d);
            System.out.println(" send order  server succeed.  times:" + d);
            String resp = in.readLine();
            System.out.println("now is :" + resp);

            Thread.sleep(1000);
        } catch (Exception ex) {

        } finally {
            if (out != null) {
                out.close();
                out = null;
            }

            if (in != null) {
                try {
                    socket.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
