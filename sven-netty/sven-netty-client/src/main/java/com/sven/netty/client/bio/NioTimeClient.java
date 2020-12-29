package com.sven.netty.client.bio;

/**
 * @author ：jian.tang@dmall.com
 * @date ：Created in 2020/12/24 9:42
 * @description：
 * @version:
 * @see
 */
public class NioTimeClient {
    public static void main(String[] args) {
        int port = 8080;
        String ip = "127.0.0.1";
        new Thread(new NioTimeClientHandler(ip,port),"time-client-001").start();
    }
}
