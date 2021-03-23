package securit;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class MD5Util {
    public static void main(String[] args) throws UnsupportedEncodingException {
       String plainText= "dassssssssssssssssdddddddddddddddddddddddssssss1";
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个md5算法！");
        }
        String md5Info=Base64.getEncoder().encodeToString(secretBytes);
        System.out.println(md5Info);
    }
}
