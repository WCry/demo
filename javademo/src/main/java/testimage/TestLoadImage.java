package testimage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author zhangxuepei
 * @since 3.0
 * Image 的Graphics是一个全局的静态变量  绘制有透明区域图片是时候
 * 多次 快速操作图片会出现图片叠加的问题
 */
public class TestLoadImage {

    public static void main(String[] args) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(new File(
                "C:\\Users\\zhangxuepei\\Desktop\\新建文件夹\\28M.jpg"))) {
            //图片解压之后的大小是图片的像素宽度*长度*位度 等于图片的大小
           BufferedImage bufferedImage = ImageIO.read(fileInputStream);
           System.out.println(bufferedImage.getData().getDataBuffer().getSize());
        }
    }

    private static void writeImage(double minXPix, double maxXPix, double minYPix, double maxYPix, String name) throws IOException {
        String tileFormat = "png";
        BufferedImage bufferedImage;
        try (FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\zhangxuepei\\Desktop\\SpringbootDataES\\dsdsa.png"))) {
            bufferedImage = ImageIO.read(fileInputStream);
        }
        File file = new File("C:\\Users\\zhangxuepei\\Desktop\\SpringbootDataES\\" + name + ".png");
        cutImage2Tile(bufferedImage, (int) minXPix, (int) minYPix, (int) maxXPix, (int) maxYPix, tileFormat, file);
    }

    /**
     * @param tileImage 原始图片
     * @param srcMinXPix
     * @param srcMinYPix
     * @param srcMaxXPix
     * @param srcMaxYPix
     * @param format 格式
     */
    public static BufferedImage cutImage2Tile(BufferedImage tileImage, int srcMinXPix, int srcMinYPix, int srcMaxXPix, int srcMaxYPix, String format, File file) throws IOException {
        // 创建切片对象
        int tileWidth = 256;
        int tileHeight = 256;
        // 创建切片对象
        BufferedImage bufferedImage = new BufferedImage(tileWidth, tileHeight, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, 256, 256, 0, 0, 256, 256, null);
        graphics.drawImage(tileImage, 0, 0, 256, 256, srcMinXPix, srcMinYPix, srcMaxXPix, srcMaxYPix, null);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            ImageIO.write(bufferedImage, format, fileOutputStream);
            bufferedImage.flush();
        }
        graphics.clearRect(0, 0, tileWidth, tileHeight);
        return bufferedImage;
    }
}
