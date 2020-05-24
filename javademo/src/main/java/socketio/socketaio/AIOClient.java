package socketio.socketaio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * user:zxp
 * Day:2020,02,16
 * AIO 异步Socket处理
 **/
public class AIOClient implements CompletionHandler<Void, AIOClient>{
   //异步Socket处理
    private AsynchronousSocketChannel client;
    private String host;
    private int port;
    private CountDownLatch latch;

    public static void main(String[] args) {
        AIOClient simpleClientAIO=new AIOClient("localhost",8080);
        simpleClientAIO.beginAIO();
    }

    public AIOClient(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            //首先通过AsynchronousSocketChannel的open方法创建一个新的AsynchronousSocketChannel对象。
            client = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void beginAIO() {
        //创建CountDownLatch进行等待，防止异步操作没有执行完成线程就退出。
        latch = new CountDownLatch(1);
        //通过connect方法发起异步操作，它有两个参数，同时注册连接成功失败的回调类
        //A attachment：AsynchronousSocketChannel的附件，用于回调通知时作为入参被传递，调用者可以自定义；
        //CompletionHandler＜Void,? super A＞ handler：异步操作回调通知接口，由调用者实现。
        client.connect(new InetSocketAddress(host, port), this, this);
        try {
            latch.await();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 完成之后进行回调
     * @param result
     * @param attachment
     */
    @Override
    public void completed(Void result, AIOClient attachment) {
        //客户端异步读取时间服务器服务端应答消息的处理逻辑
         ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        //注册一个 读的异步回调
        client.read(readBuffer,readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result,ByteBuffer buffer) {
                //从CompletionHandler的ByteBuffer中读取应答消息，然后打印结果。
                buffer.flip();
                byte[] bytes = new byte[buffer.remaining()];
                buffer.get(bytes);
                String body;
                try {
                    body = new String(bytes,"UTF-8");
                    System.out.println("Now is : " + body);
                    latch.countDown();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                //当读取发生异常时，关闭链路，
                //同时调用CountDownLatch的countDown方法让AsyncTimeClientHandler线程执行完毕，客户端退出执行。
                try {
                    client.close();
                    latch.countDown();
                } catch (IOException e) {
                    // ingnore on close
                }
            }
        });
        //
        byte[] req = "服务端：我是客户端A".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
        writeBuffer.put(req);
        writeBuffer.flip();
        //Client 发下哦那个消息给服务端，同时注册完成和失败回调
        client.write(writeBuffer, writeBuffer,
                new CompletionHandler<Integer, ByteBuffer>() {
                    @Override
                    public void completed(Integer result, ByteBuffer buffer) {
                        //如果发送缓冲区中仍有尚未发送的字节，将继续异步发送，如果已经发送完成，则执行异步读取操作。
                        if (buffer.hasRemaining()) {
                            client.write(buffer, buffer, this);
                        }
                    }
                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment) {
                        try {
                            client.close();
                            latch.countDown();
                        } catch (IOException e) {
                            // ingnore on close
                        }
                    }
                });

    }

    /**
     * 失败之后进行回调
     * @param exc
     * @param attachment
     */
    @Override
    public void failed(Throwable exc, AIOClient attachment) {
        exc.printStackTrace();
        try {
            client.close();
            latch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
