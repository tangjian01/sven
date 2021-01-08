package com.sven.netty.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @date ：Created in 2020/12/24 9:02
 * @description：
 * @version:
 * @see
 */
public class NioMultiTimeServerHandler implements Runnable {
    private int port;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private volatile  boolean stop;
    public NioMultiTimeServerHandler(int port){
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port),1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("the time server is start in port :" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    @Override
    public void run() {
        while (!stop){
            try {
                //System.out.println("listen connect");
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null ;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        handleInput(key);
                    }catch (Exception ex){

                    }
                }
            }
            catch (Exception ex){

            }
        }
        if(selector!=null){
            try {
                selector.close();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void stop(){
        this.stop = true;
    }

    private void handleInput(SelectionKey key) throws IOException {
        if(key.isValid()){
            if(key.isAcceptable()){
                ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector,SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                SocketChannel sc = (SocketChannel)key.channel();
                ByteBuffer readbuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readbuffer);
                if(readBytes>0){
                    readbuffer.flip();
                    byte[] bytes = new byte[readbuffer.remaining()];
                    readbuffer.get(bytes);
                    String body = new String(bytes,"UTF-8");

                    System.out.println("the time server receive order: "+ body);
                    String currentTime = "query time order".equalsIgnoreCase(body) ?
                            new java.util.Date(System.currentTimeMillis()).toString() :"bad order";
                    doWrite(sc,currentTime);
                }
                else if(readBytes<0){
                    key.cancel();
                    sc.close();
                }
                else{;}
            }
        }
    }

    private void doWrite(SocketChannel channel , String response) throws IOException {

        if(response!=null && response.trim().length()>0){
            System.out.println("doWrite :" + response);
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            channel.write(writeBuffer);
        }
    }
}
