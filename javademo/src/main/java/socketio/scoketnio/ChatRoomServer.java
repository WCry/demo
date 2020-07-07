package socketio.scoketnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * user:zxp
 * Day:2020,02,18
 * NIO  不需要那么多线程了
 **/
public class ChatRoomServer {
    private final String TAG = "welcome";
    private final String SEPECTOR = "####";
    private Selector selector;//选择器
    private ByteBuffer buffer = ByteBuffer.allocate(1024);//缓冲区
    private Map<String, String> clientMap = new HashMap<>();//记录客户端信息
    private List<SocketChannel> socketChannelList = new ArrayList<>();

    /**
     * 创建服务端通道
     *
     * @param port
     *
     * @throws IOException
     */
    public ChatRoomServer(int port) throws IOException {
        //创建通道
        ServerSocketChannel sschannel = null;
        sschannel = ServerSocketChannel.open();
        //绑定端口
        sschannel.bind(new InetSocketAddress(port));
        //设置非阻塞模式
        sschannel.configureBlocking(false);
        //获取selector
        selector = Selector.open();
        //注册通道到selectror
        sschannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("server is running at" + sschannel.getLocalAddress());

    }

    /**
     * 启动聊天服务器
     *
     * @param args
     *
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ChatRoomServer myServer = new ChatRoomServer(10086);
        myServer.listen();
    }

    /**
     * 循环不断的监听客户端的链接
     *
     * @throws IOException
     */
    public void listen() throws IOException {
        while (true) {
            //阻塞，直至有事件就绪
            selector.select();
            //获取SelectionKey集合，使用迭代器读取SelectionKey
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //读到SelectionKey
                SelectionKey sk = iterator.next();
                //移除已经处理的SelectionKey
                iterator.remove();
                //处理SelectionKey
                handleSelectionKey(sk);
            }
            //处理完毕，清空SelectionKey集合
            selector.selectedKeys().clear();
        }
    }

    /**
     * 处理SelectionKey
     *
     * @param sk 　SelectionKey
     */
    private void handleSelectionKey(SelectionKey sk) throws IOException {
        ServerSocketChannel serverSocketChannel;
        SocketChannel socketChannel;
        if (sk.isAcceptable()) {//可接收
            //从SelectionKey中获取 ServerSocketChannel通道
            serverSocketChannel = (ServerSocketChannel) sk.channel();
            //接受来自客户端的连接
            socketChannel = serverSocketChannel.accept();
            //设置新连接的通道为非阻塞
            socketChannel.configureBlocking(false);
            //将该通道注册到selectror上,并设置对读事件感兴趣
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("[" + socketChannel.getRemoteAddress() + "] is already connected to the server!");
            //告诉客户端输入昵称
            writeMsg("请输入昵称,进行身份确认后聊天：", socketChannel);
        } else if (sk.isReadable()) {//读就绪
            //获取通道
            socketChannel = (SocketChannel) sk.channel();
            //接收消息
            String msg = readMsg(socketChannel);
            //处理消息,若为１部分，则是昵称消息；若为２部分，则是群聊消息；若为３部分，则是私聊消息；
            String[] msgArray = msg.split(SEPECTOR);
            //防止msg为空
            if (msg == null || "".equals(msg)) {
                System.out.println(clientMap);
                String addr = socketChannel.getRemoteAddress().toString();
                String nickname = clientMap.get(addr);
                System.out.println(nickname + "断开了连接");
                clientMap.remove(addr);
                socketChannelList.remove(socketChannel);
                socketChannel.close();//关闭通道
                broadcastMsg(nickname + "已经退出了群聊" + ",当前在线人数：" + clientMap.size());

                return;
            }
            if (msgArray.length == 1 && !"".equals(msg)) {
                //昵称可能重复
                if (clientMap.containsValue(msgArray[0])) {
                    writeMsg("该昵称已经存在,请重新输入：", socketChannel);
                } else {
                    clientMap.put(socketChannel.getRemoteAddress().toString(), msgArray[0]);
                    socketChannelList.add(socketChannel);
                    writeMsg(TAG + "," + msgArray[0], socketChannel);
                    broadcastMsg2(msgArray[0] + "加入了群聊", socketChannel);
                }
            } else if (msgArray.length == 2) {
                System.out.println(msgArray[0] + " said to all:" + msgArray[1]);
                //广播消息
                broadcastMsg(msgArray[0] + ":" + msgArray[1]);
            } else if (msgArray.length == 3) {
                System.out.println("[" + msgArray[0] + "私聊" + msgArray[2] + ":" + msgArray[1] + "]");
                p2pChat(msgArray[0] + ":" + msgArray[1], msgArray[2], socketChannel);
            }

        }

    }

    /**
     * 接收消息
     *
     * @param socketChannel
     *
     * @throws IOException
     */
    private String readMsg(SocketChannel socketChannel) throws IOException {
        //初始化缓冲区
        buffer.clear();
        int len = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while ((len = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            stringBuilder.append(new String(buffer.array(), 0, len, StandardCharsets.UTF_8));
        }
        return stringBuilder.toString();
    }

    /**
     * 发送消息
     *
     * @param str
     * @param socketChannel
     *
     * @throws IOException
     */
    private void writeMsg(String str, SocketChannel socketChannel) throws IOException {
        buffer.clear();
        buffer.put(str.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        socketChannel.write(buffer);

    }

    /**
     * 群发消息，方法一
     *
     * @param msg
     *
     * @throws IOException
     */
    private void broadcastMsg(String msg) throws IOException {
        for (SelectionKey key : selector.keys()) {
            Channel target = key.channel();
            if (target.isOpen() && target instanceof SocketChannel) {
                writeMsg(msg, (SocketChannel) target);
            }
        }
    }

    /**
     * 群发消息，方法二
     *
     * @param msg
     * @param socketChannel
     *
     * @throws IOException
     */
    private void broadcastMsg2(String msg, SocketChannel socketChannel) throws IOException {
        for (SocketChannel channel : socketChannelList) {
            if (channel.isOpen() && !channel.equals(socketChannel)) {
                buffer.clear();
                buffer.put(msg.getBytes());
                buffer.flip();
                channel.write(buffer);
            }
        }
    }

    /**
     * 私发消息
     *
     * @param msg
     * @param hisname
     * @param sourcechannel 　－－源通道
     *
     * @throws IOException
     */
    private void p2pChat(String msg, String hisname, SocketChannel sourcechannel) throws IOException {
        boolean flag = false;//记录是否发成功发送给指定用户
        for (SelectionKey sk : selector.keys()) {
            Channel target = sk.channel();
            if (target.isOpen() && target instanceof SocketChannel) {
                SocketChannel socketChannel = (SocketChannel) target;
                String temname = clientMap.get(socketChannel.getRemoteAddress().toString());
                if (temname.equals(hisname)) {
                    writeMsg(msg, socketChannel);
                    flag = true;
                    break;
                }
            }

        }
        if (!flag) {
            writeMsg("该用户不存在", sourcechannel);
        } else {
            writeMsg(msg + "---------【私聊发送给：" + hisname + "　　　状态：成功】", sourcechannel);
        }
    }
}
