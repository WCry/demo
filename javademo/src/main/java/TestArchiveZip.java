import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestArchiveZip {
    public static void main(String[] args) throws IOException {
        String baseFile = "C:\\Users\\zhangxuepei\\Desktop\\新建文件夹\\aa\\";
        String inputFile1 = baseFile + "a/3M.jpg";
        String inputFile2 = baseFile + "b/3M.jpg";
        String outputFile = baseFile + "dasd.zip";
        //创建zip输出流
        ZipArchiveOutputStream out = new ZipArchiveOutputStream(new FileOutputStream(outputFile));
        //创建缓冲输出流
        BufferedOutputStream bos = new BufferedOutputStream(out);
        //如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        String zipName = inputFile1.replace(baseFile, "");
        out.putArchiveEntry(new ZipArchiveEntry(zipName));
        try (FileInputStream fos = new FileInputStream(inputFile1);
             BufferedInputStream bis = new BufferedInputStream(fos);) {
            int len = -1;
            //将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            bos.flush();
        }
        //每一个压缩完成记得关闭
        out.closeArchiveEntry();
        zipName = inputFile2.replace(baseFile, "");
        //如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
        out.putArchiveEntry(new ZipArchiveEntry(zipName));
        try (FileInputStream fos = new FileInputStream(inputFile2);
             BufferedInputStream bis = new BufferedInputStream(fos);) {
            int len = -1;
            //将源文件写入到zip文件中
            byte[] buf = new byte[1024];
            while ((len = bis.read(buf)) != -1) {
                bos.write(buf, 0, len);
            }
            //记得将缓冲流 缓冲写入一下
            bos.flush();
        }
        //每一个压缩完成记得关闭
        out.closeArchiveEntry();
        out.close();
    }
}
