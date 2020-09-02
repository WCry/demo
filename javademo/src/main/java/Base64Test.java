import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * user:zxp
 * Day:2020,06,05
 **/
public class Base64Test {
    public static void main(String[] args) throws IOException {
        /**
         * Base64 变种
         *  Base64 Basic
         *  Base64 MIME
         *  Base64  RFC4648 用于URL编码
         */
       File dasd=new File("C:\\Users" +
               "\\zhangxuepei\\Desktop" +
               "\\新建文件夹\\28M.jpg");

        byte[] bytes=new byte[(int)dasd.length()];
        new FileInputStream(dasd).read(bytes);
        byte[] target= Base64.getEncoder().encode(bytes);
        System.out.println(target.length);
    }
}
