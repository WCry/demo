import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class BufferImageTest {
    public static void main(String[] args) throws IOException {
        File file=new File("C:\\Users\\zhangxuepei\\Desktop\\新建文件夹\\dddd.png");
        Image image=new Image(new FileInputStream(file));
        System.out.println(image.getHeight());
        BufferedImage bufferedImage= ImageIO.read(new File("C:\\Users\\zhangxuepei\\Desktop\\新建文件夹\\dddd.png"));
        System.out.println(bufferedImage.getWidth());
    }
}


