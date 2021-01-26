package com.sven.netty.client.codec;

import com.sven.netty.client.dto.UserInfo;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/11 9:43
 * @description：
 * @version:
 * @see
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] userInfos = userInfos();
        for (UserInfo userInfo : userInfos) {
            ctx.write(userInfo);
        }
        ctx.flush();
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive the msgpack message is " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private UserInfo[] userInfos() {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo;
        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setName(" -- " + i);
            userInfos[i] = userInfo;
        }

        return userInfos;
    }
}
