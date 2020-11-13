import org.geotools.data.FeatureReader;
import org.geotools.data.Query;
import org.geotools.data.Transaction;
import org.geotools.data.oracle.OracleNGDataStoreFactory;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.jdbc.JDBCDataStoreFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestOracleDataStoreReader {
    public static void main(String[] args) throws IOException {
        TestOracleDataStoreReader testOracleDataStoreReader =new TestOracleDataStoreReader();
        testOracleDataStoreReader.connectionOracle();
    }

    private void connectionOracle() throws IOException {
        Map<String, Serializable> params = new HashMap();
        params.put(JDBCDataStoreFactory.DBTYPE.key, "数据库名称");
        params.put(JDBCDataStoreFactory.HOST.key, "IP");
        params.put(JDBCDataStoreFactory.PORT.key, "1521");
        params.put(JDBCDataStoreFactory.DATABASE.key, "数据库");
        params.put(JDBCDataStoreFactory.USER.key, "用户名");
        params.put(JDBCDataStoreFactory.PASSWD.key, "密码");
        //设置等待时间
        params.put(JDBCDataStoreFactory.MAXWAIT.key, 500);
        //设置最大链接数
        params.put(JDBCDataStoreFactory.MAXCONN.key, 100);
        //设置一次批量处理数据大小，建议设置100条 批量处理一次
        params.put(JDBCDataStoreFactory.BATCH_INSERT_SIZE.key, 100);
        // params.put(JDBCDataStoreFactory.PK_METADATA_TABLE.key,"user_constraints");
        params.put(JDBCDataStoreFactory.SCHEMA.key, "数据库格式");
        //针对于 主键是否暴露 主键不暴露无法对于主键进行操作
        //params.put(JDBCDataStoreFactory.EXPOSE_PK.key, true);
        OracleNGDataStoreFactory m_JDBCDataStoreFactory = new OracleNGDataStoreFactory();
        JDBCDataStore jdbcDataStore= m_JDBCDataStoreFactory.createDataStore(params);
        String[] returnName = new String[1];
        Query query = new Query();
        returnName[0] = "NAME";
        query.setPropertyNames(returnName);
        query.setTypeName("POI2");
        jdbcDataStore.getFeatureSource("POI2").getCount(null);

        FeatureReader<SimpleFeatureType, SimpleFeature> featureSource = jdbcDataStore.getFeatureReader(query,
                Transaction.AUTO_COMMIT);
    }
}
