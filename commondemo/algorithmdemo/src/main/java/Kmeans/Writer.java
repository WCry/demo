package Kmeans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Writer {

    public static void main(String[] args) {
        new Writer().run();
    }

    //数据范围：-range到range
    private int range = 100;
    //数据总数
    private int total = 50;

    //生成数据文件
    private void run() {
        File file = new File("data4");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < total; i++) {
                String str = "B" + String.valueOf(i);
                Random r = new Random();
                for (int j = 0; j < Metadata.DATA_VECTOR_SIZE; j++) {
                    int data = r.nextInt(range * 2) - range;
                    str += " " + String.valueOf(data);
                }
                bw.write(str + "\n");
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}