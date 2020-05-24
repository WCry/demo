package socketio.socketaio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * user:zxp
 * Day:2020,02,16
 **/
public class AIOServer {
    //服务端口
    final int  SERVER_PORT = 8080;
    private CountDownLatch latch=new CountDownLatch(1);
    public static void main(String[] args) throws IOException, InterruptedException {
        AIOServer simpleServerAIO=new AIOServer();
        simpleServerAIO.startListener();

    }
    public AIOServer(){
    }
    public void startListener() throws IOException, InterruptedException {
        final AsynchronousServerSocketChannel listener =
                AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(SERVER_PORT));
        //注册有连接成功之后的回调处理
        listener.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
            public void completed(AsynchronousSocketChannel ch, Void att) {
                //listener.accept(null, this);// 接受下一个连接
                handle(ch);// 处理当前连接
            }
            @Override
            public void failed(Throwable exc, Void attachment) {
               // TODO Auto-generated method stub
                //连接失败处理
            }
        });
        //阻塞起来
        latch.await();
    }

    public void handle(AsynchronousSocketChannel ch) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);//开一个 Buffer
        //attachment 作为一个依附判断使用，判断是哪个线程的处理对象
        //所以这里可以随意定义一个对象，如果不定义可以使用null  void 等对象
        ch.read(byteBuffer,"sa", new CompletionHandler<Integer, Object>() {
            @Override
            public void completed(Integer result, Object attachment) {
                //从CompletionHandler的ByteBuffer中读取应答消息，然后打印结果。
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                String body;
                try {
                    body = new String(bytes,"UTF-8");
                    System.out.println("客户端消息 : " + body);
                    //向客户端回复消息
                    byte[] req = "服务端：客户端A请接收消息".getBytes();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
                    writeBuffer.put(req);
                    writeBuffer.flip();
                    ch.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {

                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                        }
                    });

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });

    }
}