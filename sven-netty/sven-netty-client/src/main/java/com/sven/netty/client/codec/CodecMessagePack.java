package com.sven.netty.client.codec;

import lombok.extern.slf4j.Slf4j;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @date ：Created in 2021/1/8 9:12
 * @description：
 * @version:
 * 编码、解码
 */
@Slf4j
public class CodecMessagePack {
    public static void main(String[] args) throws IOException {
        List<String> src = new ArrayList<>();
        src.add("msgpack");
        src.add("protobuf");
        src.add("marksharling");
        src.add("thrift");

        MessagePack messagePack = new MessagePack();
        //Serialize
        byte[] write = messagePack.write(src);
        log.info("write :{}",write);
        //Deserialize
        List<String> dst1 = messagePack.read(write, Templates.tList(Templates.TString));

        System.out.println(dst1.get(0));
        System.out.println(dst1.get(1));
        System.out.println(dst1.get(2));

        log.info("OK");
    }
}
