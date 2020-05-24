package channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestChannel
{
    public static void main(String[] args) throws FileNotFoundException {
        //NIO中的channel
        //https://www.jianshu.com/p/eb9d23113dfa
        //https://www.cnblogs.com/ostenant/p/9695183.html
        FileChannel fileChannel=new FileInputStream(new File("")).getChannel();
        SocketChannel socketChannel=new Socket(null).getChannel();
    }
}
