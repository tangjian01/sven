package com.sven.netty.client.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/11 9:38
 * @description：
 * @version:
 * @see
 */
public class MessageTest {
    private final String post;
    private final int port;
    private final int sendNumber;

    public MessageTest(String post, int port, int sendNumber) {
        this.post = post;
        this.port = port;
        this.sendNumber = sendNumber;
    }

    public void run() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast("message decoder", new MsgpackDecoder());
                        ch.pipeline().addLast("message decoder", new MsgpackEncoder());

                        ch.pipeline().addLast(new EchoClientHandler(sendNumber));
                    }
                });

    }
}
