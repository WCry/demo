package ShpFile;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFactorySpi;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureReader;
import org.geotools.data.FeatureSource;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.oracle.OracleNGDataStoreFactory;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.JDBCDataStoreFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestShpFileReader {
    public static void main(String[] args) throws IOException {
        TestShpFileReader testShpFileReader=new TestShpFileReader();
        testShpFileReader.dss();
//        File file = new File("D:\\道路\\R.shp");
//        Map<String, Object> map = new HashMap<>();
//        map.put("url", file.toURI().toURL());
//        DataStore dataStore = DataStoreFinder.getDataStore(map);
//        String typeName = dataStore.getTypeNames()[0];
//        String[] returnName = new String[1];
//        Query query = new Query();
//        returnName[0] = "SnodeID as dd";
//        query.setPropertyNames(returnName);
//        query.setTypeName(typeName);
//
//        FeatureReader<SimpleFeatureType, SimpleFeature> featureSource = dataStore.getFeatureReader(query, Transaction.AUTO_COMMIT);
//        featureSource.getFeatureType().getTypes().forEach(attributeType -> {
//            System.out.println(attributeType.getName());
//        });
    }

    private void dss() throws IOException {
        Map<String, Serializable> params = new HashMap();
        params.put(JDBCDataStoreFactory.DBTYPE.key, "Oracle");
        params.put(JDBCDataStoreFactory.HOST.key, "10.19.154.66");
        params.put(JDBCDataStoreFactory.PORT.key, "1521");
        params.put(JDBCDataStoreFactory.DATABASE.key, "GEOG");
        params.put(JDBCDataStoreFactory.USER.key, "ZHE");
        params.put(JDBCDataStoreFactory.PASSWD.key, "ZHE");
        //设置等待时间
        params.put(JDBCDataStoreFactory.MAXWAIT.key, 500);
        //设置最大链接数
        params.put(JDBCDataStoreFactory.MAXCONN.key, 100);
        //设置一次批量处理数据大小，建议设置100条 批量处理一次
        params.put(JDBCDataStoreFactory.BATCH_INSERT_SIZE.key, 100);
        // params.put(JDBCDataStoreFactory.PK_METADATA_TABLE.key,"user_constraints");
        params.put(JDBCDataStoreFactory.SCHEMA.key, "ZHE");
        //针对于 主键是否暴露 主键不暴露无法对于主键进行操作
        //params.put(JDBCDataStoreFactory.EXPOSE_PK.key, true);
        OracleNGDataStoreFactory m_JDBCDataStoreFactory = new OracleNGDataStoreFactory();
        JDBCDataStore jdbcDataStore= m_JDBCDataStoreFactory.createDataStore(params);
        String[] returnName = new String[1];
        Query query = new Query();
        returnName[0] = "NAME";
        query.setPropertyNames(returnName);
        query.setTypeName("POI2");
        FeatureReader<SimpleFeatureType, SimpleFeature> featureSource = jdbcDataStore.getFeatureReader(query,
                Transaction.AUTO_COMMIT);
    }
}
