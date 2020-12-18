package com.sven.netty.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：jian.tang@dmall.com
 * @date ：Created in 2020/12/18 9:12
 * @description：
 * @version:
 * @see
 */
public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println(" current thread is :" + Thread.currentThread().getName());
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (true) {
                body = in.readLine(); // 当前socekt下的数据接收
                if (body == null)
                    break;
                System.out.println("the time server receive data :" + body);
                currentTime = " query time order".equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentTime); //当前socekt下的数据应答
            }
        } catch (Exception ex) {
            if(in !=null ){
                try{
                    in.close();
                }catch (Exception el){
                    el.printStackTrace();
                }
            }
            if(out!=null){
                out.close();
                out = null;
            }

            if(this.socket!=null){
                try {
                    this.socket.close();
                }
                catch (Exception es){
                    es.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
