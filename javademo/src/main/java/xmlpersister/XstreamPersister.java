package xmlpersister;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlMap;

import java.io.*;

public class XstreamPersister{
    public static void main(String[] args) {
        Person bean=new Person("张三",19);
      //  XStream xstream = new XStream(new DomDriver());
        XStream xstream = new XStream(new StaxDriver());
        xstream.alias("人",Person.class);//为类名节点重命名
        //隐藏掉节点
        xstream.omitField(Person.class, "name");
        //XML序列化
        String xml = xstream.toXML(bean);
        System.out.println(xml);
        //XML反序列化
        bean=(Person)xstream.fromXML(xml);
        System.out.println(bean);
        PersistenceStrategy strategy = new FilePersistenceStrategy(new File("D:\\tmp"),xstream);
        strategy.put("test",new Person("张三",13));
        XmlMap xmlMap = new XmlMap(strategy);
        try {
            xstream.toXML(new Person("张三",13),new FileOutputStream(new File("D:\\tmp\\text.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        xmlMap.
//        xmlMap.put("test",new Person("张三",13));
//        List list = new XmlArrayList(strategy);
//        list.add(new Person("张三",13));//保存数据
//        list.add(new Person("李四",21));
//        list.add(new Person("王五",17));


//        xstream = new XStream(new JettisonMappedXmlDriver());
//        xstream.setMode(XStream.NO_REFERENCES);
//        //Json序列化
//        String json=xstream.toXML(bean);
//        System.out.println(json);
//        //Json反序列
//        bean=(Person)xstream.fromXML(json);
//        System.out.println(bean);
    }
}
