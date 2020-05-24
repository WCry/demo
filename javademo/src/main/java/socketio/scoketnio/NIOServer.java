package socketio.scoketnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * user:zxp
 * Day:2020,02,18
 * NIO  不需要那么多线程了
 **/
public class NIOServer {
    private static final int BUF_SIZE=1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;
    public static void main(String[] args)
    {
        NIOServer nioServer=new NIOServer();
        nioServer.beginSelector();
    }

    /**
     * 开启服务端ServerSocketChannel
     * Selector
     */
    public  void beginSelector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try{
            selector = Selector.open();
            ssc= ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            //注册Selector  注册选择器
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while(true){
                //判断在时间内 是否读取到对象
                if(selector.select(TIMEOUT) == 0){
                    System.out.println("==");
                    continue;
                }
                //读取到对象，遍历读取到的对象
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    //获取读取的key
                    SelectionKey key = iter.next();
                    //判断读取的字节是否可以被处理
                    if(key.isAcceptable()){
                        handleAccept(key);
                    }
                    //判断是否可以读
                    if(key.isReadable()){
                        handleRead(key);
                    }
                    //判断是否可以写和key 是否有效
                    if(key.isWritable() && key.isValid()){
                        handleWrite(key);
                    }
                    //判断key是否还在链接
                    if(key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    //读取完成 从迭代器中移除掉
                    iter.remove();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(selector!=null){
                    selector.close();
                }
                if(ssc!=null){
                    ssc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 处理接受 连接被接受
     * @param key
     * @throws IOException
     */
    public static void handleAccept(SelectionKey key) throws IOException {
        //获取对应的ServerSocketChannel
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }
    public static void handleRead(SelectionKey key) throws IOException{
        //获取对应的Socket
        SocketChannel sc = (SocketChannel)key.channel();
        //获取字节流
        ByteBuffer buf = (ByteBuffer)key.attachment();
        long bytesRead = sc.read(buf);
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            //字节流清楚
            buf.clear();
            //继续读取
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
            //读取完成 关闭链接
            sc.close();
        }
    }
    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            sc.write(buf);
        }
        //压缩缓冲区  将未读完的放到最左边 供下一次读取
        buf.compact();
    }

}
