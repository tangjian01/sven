package com.sven.netty.client.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @author ：897863952@qq.com
 * @date ：Created in 2021/1/11 9:34
 * @description：
 * @version:
 * @see
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        final byte[] array;
        final int length = msg.readableBytes();
        array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length); // 写入到数组
        MessagePack msgPack = new MessagePack();
        out.add(msgPack.read(array));//反序列化
    }
}
