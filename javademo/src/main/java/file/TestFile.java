package file;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestFile {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\zhangxuepei\\Desktop\\新建文件夹\\新建文件夹\\das.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("创建文件失败！");
            //e.printStackTrace();
        }
    }
}
