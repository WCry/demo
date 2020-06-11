package cache;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestFIFO {
    public static void main(String[] args) throws IOException, InterruptedException {
        FIFOTimerCache<FileOutputStream> fileOutputStreamFIFOTimerCache = new FIFOTimerCache<>(5, 4000);
        for (int i = 0; i < 100000; i++) {
            int remainderNumber = i % 5;
            File file = new File("C:\\Users\\zhangxuepei\\Desktop\\新建文件夹\\path" + remainderNumber);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileInputStream =fileOutputStreamFIFOTimerCache.get(file.getPath());
            if(fileInputStream==null){
                fileInputStream=new FileOutputStream(file);
                fileOutputStreamFIFOTimerCache.put(file.getPath(),fileInputStream);
            }
            byte[] bytes=new byte[1024*10*10];
            fileInputStream.write(bytes);
            Thread.sleep(90);
            fileOutputStreamFIFOTimerCache.canRemove();
        }
    }




}
