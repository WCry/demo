package socketio.scoketnio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * user:zxp
 * Day:2020,02,18
 * NIO的Socket的客户端
 * 关于NIO讲解https://blog.csdn.net/SUPERCHAT8/article/details/100592863
 **/
public class ChatRoomClient {
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);//缓冲区
    private Selector selector = null;//选择器
    private boolean hasNickname = false;
    private String nickname = "user";
    //输入昵称的返回字符串标记，如：welocme,张三
    private final String TAG = "welcome";
    private final String SEPECTOR = "####";
    /**
     * 启动聊天客户端
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        new ChatRoomClient().start();
    }
    /**
     * 启动通道和selector
     * @throws IOException
     */
    public void start() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 10086));
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("客户端已经启动");
        //开启线程，接受消息
        receiveMsgThread();
        //主线程发送消息
        Scanner scanner = new Scanner(System.in);
        byteBuffer = ByteBuffer.allocate(1024);
        while (true) {
            String input = scanner.nextLine();
            if (hasNickname) {//有昵称后才能进行聊天
                //单聊格式：msg@对方昵称
                if (input.contains("@")) {
                    input = input.replace("@", SEPECTOR);
                }
                //发送消息,服务端根据分隔符将消息分割后，若为2部分，则是群聊消息；若为３部分,则是私发消息
                writeMesage(nickname + SEPECTOR + input, socketChannel);
            } else {//设置昵称
                writeMesage(input, socketChannel);
            }

        }

    }

    /**
     * 接受消息
     * @param socketChannel
     * @return
     * @throws IOException
     */
    private String readMessage(SocketChannel socketChannel) throws IOException {
        byteBuffer.clear();
        int len = 0;
        StringBuilder builder = new StringBuilder();
        while ((len = socketChannel.read(byteBuffer)) > 0) {
            byteBuffer.flip();
            builder.append(new String(byteBuffer.array(), 0, len,"UTF-8"));
        }
        return builder.toString();

    }

    /**
     * 发送消息
     * @param str
     * @param socketChannel
     * @throws IOException
     */
    private void writeMesage(String str, SocketChannel socketChannel) throws IOException {
        byteBuffer.clear();
        byteBuffer.put(str.getBytes(StandardCharsets.UTF_8));
        byteBuffer.flip();
        socketChannel.write(byteBuffer);

    }
    /**
     * 接收消息的线程
     */
    private void receiveMsgThread() {
        new Thread(() -> {
            SocketChannel socketChannel = null;
            while (true) {
                try {
                    selector.select();
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isReadable()) {
                            socketChannel = (SocketChannel) selectionKey.channel();
                            String msgReciv = readMessage(socketChannel);
                            System.out.println(msgReciv);
                            //连接成功时，服务端要求客户端输入昵称，若返回约定的标志，说明昵称设置成功
                            if (msgReciv.contains(TAG)) {
                                hasNickname = true;
                                nickname = msgReciv.substring(TAG.length() + 1);//标记逗号后的字符串为昵称
                            }
                        }
                    }
                    selector.selectedKeys().clear();
                } catch (IOException e) {
                    if (socketChannel != null) {
                        try {
                            socketChannel.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


}
