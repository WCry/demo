import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestPath {
    public static void main(String[] args) {
        Path path= FileSystems.getDefault().getPath("/dasd/dasd/dasd");
        System.out.println(path.subpath(1,2));
    }
}
