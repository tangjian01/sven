package com.sven.netty.client.bio;

import java.io.IOException;
import java.net.CacheRequest;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author ：jian.tang@dmall.com
 * @date ：Created in 2020/12/24 9:44
 * @description：
 * @version:
 * @see
 */
public class NioTimeClientHandler implements Runnable {
    private String host;
    private int port;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile  boolean stop;
    public NioTimeClientHandler(String ip, int port) {
        this.host = ip;
        this.port = port;
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {
        try {
            doConnect();
        }
        catch (Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }

        while (!stop){
            try{
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try
                    {
                        handleInput(key);
                    }
                    catch (Exception ex){
                        if(key!=null){
                            key.cancel();
                            if(key.channel()!=null)
                                key.channel().close();
                        }
                    }
                }
            }
            catch (Exception ex){}
        }

        if(selector!=null){
            try{
                selector.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void doConnect() throws IOException {
        if(socketChannel.connect(new InetSocketAddress(host,port))){
            socketChannel.register(selector,SelectionKey.OP_READ);
            doWrite(socketChannel);
        }else {
            socketChannel.register(selector,SelectionKey.OP_CONNECT);
        }
    }
    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            SocketChannel sc = (SocketChannel)key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect()){
                    sc.register(selector,SelectionKey.OP_READ)
                            ;
                    doWrite(sc);
                }
                else{
                    System.exit(1);
                }
            }

            if(key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if(readBytes>0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    System.out.println("now is :" + body);
                    this.stop = true;
                }
                else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }
                else{;}
            }
        }
    }

    private void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] req = "query time order".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
        if(!writeBuffer.hasRemaining()){
            System.out.println("send order 2 server succeed.");
        }
    }
}
