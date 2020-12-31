package com.sven.netty.server.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author ：jian.tang@dmall.com
 * @date ：Created in 2020/12/31 9:16
 * @description：
 * @version:
 * @see
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new NioTimeServerHandler());
    }
}
