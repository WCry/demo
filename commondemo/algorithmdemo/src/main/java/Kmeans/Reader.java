package Kmeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    /**
     * @return 数据记录
     */
    public static List<Metadata> getDatabase() {
        // 保存数据记录
        List<Metadata> db = new ArrayList<Metadata>();
        try {
            // 打开文件
            File file = new File("data4");
            if (file.isFile() && file.exists()) {
                InputStreamReader read =
                        new InputStreamReader(new FileInputStream(file));
                BufferedReader reader = new BufferedReader(read);
                String line = null;
                // 读入文件数据
                while ((line = reader.readLine()) != null) {
                    String[] strToknizer = line.split(" ");
                    Metadata newdata = new Metadata(strToknizer);
                    db.add(newdata);
                }
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db;
    }
}
