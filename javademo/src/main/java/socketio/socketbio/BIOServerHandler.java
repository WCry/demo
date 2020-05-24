package socketio.socketbio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * user:zxp
 * Day:2020,02,18
 * BIO 服务端处理
 **/
public class BIOServerHandler extends Thread {
    private Socket socket;
    public BIOServerHandler(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        super.run();
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String returnMessage = null;
            String clientMessage = null;
            while (true) {
                //读取过程 是阻塞的
                clientMessage = in.readLine();
                if (clientMessage == null) break;
                System.out.println("客户端消息:" + clientMessage);
                returnMessage = "你好，服务端已经接受到你的信息，正在处理中";
                //将信息发送到客户端
                out.println(returnMessage);
            }
        } catch (IOException e) {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            if (out != null) {
                out.close();
            }
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
