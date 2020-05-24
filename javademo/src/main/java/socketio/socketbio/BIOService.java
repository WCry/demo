package socketio.socketbio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * user:zxp
 * Day:2020,02,18
 * BIO 是面向连接的阻塞
 * 如果需要多链接并发处理需要多线程处理，增加上线程池处理
 **/
public class BIOService {
    public static void main(String[] args) {
        //服务端口
        final int  SERVER_PORT = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            Socket socket = null;
            System.out.println("BIO服务端,创建成功");
            while (true) {
                //等待客户端的连接，这个方法是阻塞的，
                // 一直等到有客户端连接，线程才会被唤醒
                socket = serverSocket.accept();
                //每一个连接需要创建一个线程处理，创建一个线程处理
                BIOServerHandler bioServerHandler=new BIOServerHandler(socket);
                bioServerHandler.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("服务端关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}