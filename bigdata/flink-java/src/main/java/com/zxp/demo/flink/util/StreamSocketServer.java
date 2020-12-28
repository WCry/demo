package com.zxp.demo.flink.util;

import java.io.*;
import java.net.*;

/**
 * @author zhangxuepei
 * @since 3.0
 * 作为给Flink 流计算 发送数据的服务端
 */
public class StreamSocketServer {
    private static final int PORT = 9000;
    public static void main(String[] args) {
        test();
    }
    public static void test() {
        ServerSocket server;
        Socket socket;
        DataOutputStream out;
        try {
            server = new ServerSocket(PORT);
            socket = server.accept();
            out = new DataOutputStream(socket.getOutputStream());
            int time = 0;
            int num = 0;
            while (true) {
                Thread.sleep(100);
                time = time + 100;
                String MYstr = getRandomStr();
                out.writeUTF(MYstr);
                num = num + 4;
                System.out.println("我发" + MYstr + "序号是" + num + "时间是" + System.currentTimeMillis());
                out.flush();
                if (time >= 60000) {
                    System.out.println("我沉睡了" + System.currentTimeMillis());
                    Thread.sleep(120);
                    time = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 生产ItemName随机函数
     *
     * @param
     *
     * @return
     */
    private static String getRandomStr() {
        String str = "";
        int q = (int) (Math.random() * 30);
        int x = (int) (Math.random() * 200);
        int y = (int) (Math.random() * 300);
        int z = (int) (Math.random() * 10);
        str = q + " " + x + " " + y + " " + z;
        return str;
    }


}
