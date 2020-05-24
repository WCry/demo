package socketio.scoketnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * user:zxp
 * Day:2020,02,18
 * NIO的Socket的客户端
 * 关于NIO讲解https://blog.csdn.net/SUPERCHAT8/article/details/100592863
 **/
public class NIOClient {
    //NIO Client选择器
    private Selector selector;
    private String host;
    private int port;
    private SocketChannel socketChannel;
    private volatile boolean stop;
    private boolean needSendMessage=false;
    private String senMessage="";
    public static void main(String[] args) throws IOException {
        NIOClient nioClient = new NIOClient();
        nioClient.begin();
    }
    public void sendMessage(String senMessage){
        this.senMessage=senMessage;
        needSendMessage=true;
    }

    public void begin() throws IOException {
        this.host="localhost";
        this.port = 8080;
        //创建选择器
        selector = Selector.open();
        selector.select(1000);
        // SocketChannel 绑定客户端的本地地址
        socketChannel = SocketChannel.open();
        // 客户端设置为非阻塞模式
        socketChannel.configureBlocking(false);
        //根据IP和端口号做连接，并将socketChannel绑定到 selector上
        if (socketChannel.connect(new InetSocketAddress(host, port))) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel,"你好服务端!");
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        SelectionKey key = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            iterator.remove();
            try {
                //将将要通信的key
                handleInput(key);
            } catch (Exception e) {
                if (key != null) {
                    key.cancel();
                    if (key.channel() != null) {
                        key.channel().close();
                    }
                }
            }
        }
    }

    /**
     * 真正处理网络请求
     *
     * @param key
     * @throws IOException
     */
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            System.out.println("客户端请求建立的连接成功了吗？ " + socketChannel.finishConnect());
            //判断是否连接成功
            if (key.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    System.out.println("客户端连接成功");
                    //连接成功，将key注册到 Selector上 ，SelectionKey.OP_READ：监听网络中的读操作
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    // 将信息发送给 服务端
                    doWrite(socketChannel,"你好服务端");
                }
            }
            //当期的key 是可读的 ，那么读取服务端的消息（异步的读取）
            if (key.isReadable()) {
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = socketChannel.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    //将数据读从缓冲区 读取到 数组中
                    readBuffer.get(bytes);
                    String readResult = new String(bytes, "UTF-8");
                    System.out.println("服务端返回的信息：" + readResult);
                } else {  //读取不到数据
                    key.cancel();
                    socketChannel.close();
                }
            }
            if(key.isWritable()&&needSendMessage){
                //处理不间断的消息
                // 将信息发送给 服务端
                doWrite(socketChannel,senMessage);
            }
        }
    }
    /**
     * 向服务端 发送消息
     * @param socketChannel
     * @throws IOException
     */
    private void doWrite(SocketChannel socketChannel,String message) throws IOException {
        /***  将String类型的字符串 转化 成 byte数组 */
        byte[] clientMessage = (message).getBytes() ;
        //申请缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(clientMessage.length);
        //将数组的数据写入缓冲区中
        buffer.put(clientMessage);
        //调整指针的位置
        buffer.flip();
        //将数据映射到 Channel中
        socketChannel.write(buffer);
        if (!buffer.hasRemaining()) {
            System.out.println("全部信息发送成功");
        }
    }
}
