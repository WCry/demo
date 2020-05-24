package or.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
         //第一步，设置HBsae配置信息
        Configuration configuration = HBaseConfiguration.create();
        //注意。这里这行目前没有注释掉的，这行和问题3有关系  是要根据自己zookeeper.znode.parent的配置信息进行修改。
        configuration.set("zookeeper.znode.parent","/hbase-unsecure");
        //与 hbase-site-xml里面的配置信息 zookeeper.znode.parent 一致
        configuration.set("hbase.zookeeper.quorum","10.33.42.181");
        //hbase 服务地址
        configuration.set("hbase.zookeeper.property.clientPort","2181");
        //端口号
        //这里使用的是接口Admin   该接口有一个实现类HBaseAdmin   也可以直接使用这个实现类
        // HBaseAdmin baseAdmin = new HBaseAdmin(configuration);
        Admin admin = ConnectionFactory.createConnection(configuration).getAdmin();
        if(admin !=null){
            try {
                //获取到数据库所有表信息
                List<TableDescriptor> tableDescriptors = admin. listTableDescriptors();
                for (TableDescriptor hTableDescriptor : tableDescriptors) {
                    System.out.println(hTableDescriptor.getTableName());
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        SpringApplication.run(DemoApplication.class, args);
    }

}
