package TestDemo;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.io.ByteWritable;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.util.Shell;
import org.apache.hadoop.util.StringUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

public class TestHadoopFile {
    private Configuration conf = new Configuration();
    private String user = "root";
    private FileSystem fsSource;

    public static void main(String[] args) throws IOException, InterruptedException {
        TestHadoopFile testHadoopFile = new TestHadoopFile();
        testHadoopFile.initConfig();

        //testHadoopFile.writeHvtFile(new File("C:\\Users\\zhangxuepei\\Desktop\\新建文件夹 (3)"));
    }

    private void writeOneHvtFile() throws IOException, InterruptedException {
        String filePath = "";
        writeFileSystem(new File(filePath), "");
    }

    private void fileDdl() throws IOException, InterruptedException {
        FileSystem fsSource = FileSystem.get(URI.create("hdfs://10.19.154.149:9000"), conf, user);
        //创建文件
        //fsSource.createNonRecursive();
        //创建文件夹
        //fsSource.mkdirs();
        //删除文件
        //fsSource.delete()
        //true表示递归查看
        //RemoteIterator<LocatedFileStatus> rt = fs.listFiles(new Path("/result"), true);
        //0 查看块的起始范围  ；Integer.MAX_VALUE 查看块的结束范围
        //通过这两个参数控制查看的块的范围
        //BlockLocation[] data = fsSource.getFileBlockLocations(new Path("/park/putFile.txt"),
        //       0, Integer.MAX_VALUE);
    }

    private void initConfig() throws IOException, InterruptedException {
        //创建hadoop环境参数对象，通过对象的set方法设置参数
        //通过对象配置的参数优先级>配置文件的配置
        //对象配置的生效范围是当前工作线程。配置文件的生效范围是全局
        conf = new Configuration();
        //conf.set("fs.defaultFS", "hdfs://10.19.154.149:9000");
        //远程连接必须使用这个传入user 用户，基于Kerberos安全验证。只要用户对就可以不需要密码
        //连接HDFS文件系统
        //Hadoop中有多种文件系统（FileSystem有很多实现类），其中最重要的事分布式文件系统
        fsSource = FileSystem.get(URI.create("hdfs://10.19.154.149:9000"), conf, user);
        boolean ds=fsSource.delete(new Path("/test222"),true);
        System.out.println(ds);
    }

    private void writeHvtFile(File hvtDirection) throws IOException {
        FileFilter directionFilter = pathname -> pathname.isDirectory();
        File[] levelDirections = hvtDirection.listFiles(directionFilter);
        for (int i = 0; i < levelDirections.length; i++) {
            File levelDirection = levelDirections[i];
            File[] columnFiles = levelDirection.listFiles(directionFilter);
            //遍历到具体hvt文件
            for (int i1 = 0; i1 < columnFiles.length; i1++) {
                File columnFile = columnFiles[i1];
                File[] hvtFiles = columnFile.listFiles();
                for (int i2 = 0; i2 < hvtFiles.length; i2++) {
                    File hvtFile = hvtFiles[i2];
                    String targetFilePath= hvtFile.getPath().replace(hvtDirection.getPath(),"");
                    writeFileSystem(hvtFile, targetFilePath);
                }
            }
        }
    }

    private void readFile(String hdPath) throws IOException {
        Path path=new Path(hdPath);
        FSDataInputStream fsDataInputStream = fsSource.open(path);
        int bytesInt=fsDataInputStream.read();
        System.out.println(bytesInt);
    }
    public void fileSystemConfiguration() throws IOException, InterruptedException {
        // 获取配置文件对象
        //配置hdfs的路径
        conf.set("fs.defaultFS", "hdfs://10.19.154.149:9000");
        String filePath = "/user/root/demo.txt";
        //        StringBuilder sb = new StringBuilder();
        //        for (int i = 1; i < 100001; i++) {
        //            sb.append("hadoop demo " + i + "\r\n");
        //        }
        FileSystem fsSource = FileSystem.get(URI.create("hdfs://10.19.154.149:9000"), conf, user);
        FSDataOutputStream fileWrite = fsSource.create(new Path("/input/test.txt"));
        //fileWrite.write(sb.toString().getBytes());
        //创建文件件
        //fsSource.mkdirs();
        //创建文件
        // FSDataOutputStream fsdos = fsSource.create(new Path("/input/test.txt"));
        // fsdos.write(sb.toString().getBytes());
        try {
            RemoteIterator<LocatedFileStatus> iter = fsSource.listFiles(new Path("/"), true);
            //这里的第二个参数true表示递归遍历，false反之
            while (iter.hasNext()) {
                LocatedFileStatus file = iter.next();
                String Path_file = file.getPath().toString();
                // 获取文件目录
                System.out.println(user + "$:" + Path_file.substring(21));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mergeSmallFile(File[] mergeFiles) throws IOException {
        //大文件对象
        SequenceFile.Writer.Option bigFile = SequenceFile.Writer.file(new Path("/bigfile.seq"));
        SequenceFile.Writer.Option keyClassOption = SequenceFile.Writer.keyClass(Text.class);
        SequenceFile.Writer.Option valueClassOption = SequenceFile.Writer.valueClass(ByteWritable.class);
        //创建一个sequenceFile 的写 文件段落
        SequenceFile.Writer writer = SequenceFile.createWriter(conf, bigFile, keyClassOption, valueClassOption);
        Text key = new Text();
        for (File file : mergeFiles) {
            long fileSize = file.length();//获取文件的字节数大小
            byte[] fileContent = new byte[(int) fileSize];
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(fileContent, 0, (int) fileSize);//把文件的二进制流加载到fileContent字节数组中去
            String md5Str = DigestUtils.md5Hex(fileContent);
            System.out.println("merge小文件：" + file.getPath() + ",md5:" + md5Str);
            key.set(file.getPath());
            //把文件路径作为key，文件内容做为value，放入到sequencefile中
            writer.append(key, new BytesWritable(fileContent));
        }
        writer.hflush();
        writer.close();
    }

    public void readSequenceFile() throws IOException {
        //找到对应合并好的大文件
        SequenceFile.Reader.Option file = SequenceFile.Reader.file(new Path("/bigfile.seq"));
        SequenceFile.Reader reader = new SequenceFile.Reader(conf, file);
        //读取对应的key
        Text key = new Text();
        //读取对应的内容
        BytesWritable value = new BytesWritable();
        while (reader.next(key, value)) {
            //获取其中的字节流
            byte[] bytes = value.copyBytes();
            String md5 = DigestUtils.md5Hex(bytes);
            String content = new String(bytes, Charset.forName("GBK"));
            System.out.println("读取到文件：" + key + ",md5:" + md5 + ",content:" + content);
        }
    }

    public void writeFileSystem(File file, String hdTargetFile) throws IOException {
        //创建一个用于输出流
        FSDataOutputStream fileWrite = null;
        FileInputStream fileSystem=null;
        try {
            fileSystem = new FileInputStream(file);
            fileWrite = fsSource.create(new Path(hdTargetFile));
            byte[] bytes = new byte[(int)file.length()];
            fileSystem.read(bytes, 0, (int) file.length());
            //写入文件内容字节流
            fileWrite.write(bytes);
        } finally {
            if(!Objects.isNull(fileWrite)){
                fileWrite.close();
            }
            if(!Objects.isNull(fileSystem)){
                fileSystem.close();
            }
        }
    }
}
