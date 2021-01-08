package com.sven.netty.server;

/**
 * @date ：Created in 2020/12/24 9:01
 * @description：
 * @version:
 * @see
 */
public class NioTimeServer {
    public static void main(String[] args) {
        int port = 8080;
        NioMultiTimeServerHandler timeServerHandler = new NioMultiTimeServerHandler(port);

        new Thread(timeServerHandler,"NIO-MultiplexerTimeServer-001").start();
    }
}
