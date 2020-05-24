package socketio.socketbio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * user:zxp
 * Day:2020,02,18
 * 客户端接收消息线程，处理从服务端返回的消息
 * BIO  面向连接处理数据，所以必须启动一个线程处理接收消息
 **/
public class ClientAccept extends Thread {
    private Socket socket;
    private BufferedReader in = null;
    public  ClientAccept(Socket socket) throws IOException {
        this.socket=socket;
        //利用缓冲Reader
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }
    @Override
    public void run() {
        super.run();
        while (true){
            //从服务器获取到一个信息 采用一问一答的读取方式
            //接收服务器消息是阻塞的
            String resp = null;
            try {
                resp = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(resp==null){
                try {
                    //没有返回消息  睡眠等待
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                //有消息返回 输出返回消息
                System.out.println("服务端返回 : " + resp);
                //服务端返回消息需要断开了
                if(resp=="断开"){
                    //跳出不在接收消息
                    break;
                }
            }
        }
    }
}
