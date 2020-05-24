package socketio.socketbio;

import java.io.*;
import java.net.Socket;

/**
 * user:zxp
 * Day:2020,02,18
 * 关于BIO 讲解：
 * https://blog.csdn.net/SUPERCHAT8/article/details/100587673
 **/
public class BIOClient {
    //测试端口
    final int CLIENT_PORT = 8080;
    //用于本机测试
    final String HOST = "127.0.0.1";
    public static void main(String[] args) {
        BIOClient bioClient=new BIOClient();
        bioClient.postMessage();
    }
    public  void postMessage() {
        Socket socket = null;
        PrintWriter out = null;
        try {
            //创建提个Socket 连接
            socket = new Socket(HOST, CLIENT_PORT);
            //另外启一个线程处理接收消息
            ClientAccept clientAccept=new ClientAccept(socket);
            clientAccept.start();
            //打印输出  输出流  比直接使用输出操作方便
            out = new PrintWriter(socket.getOutputStream(), true);
            for (int i = 0; i <5 ; i++) {
                //向服务器端发送一个信息，这个发送完成。发送消息是非阻塞
                out.println("你好！服务端能接受到这条信息吗？");
                System.out.println("客户端发送信息成功");
            }
            //等待接收线程结束 暂时未写接收线程结束
            clientAccept.join();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if(out!=null){
                    out.close();
                }
                if(socket!=null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
