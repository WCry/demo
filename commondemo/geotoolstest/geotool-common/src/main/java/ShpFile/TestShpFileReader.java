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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestShpFileReader {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\道路\\R.shp");
        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());
        map.put("filetype","shapefile");
        map.put("charset", StandardCharsets.UTF_8);
        map.put("fstype", "shape");

        DataStore dataStore = DataStoreFinder.getDataStore(map);
        String typeName = dataStore.getTypeNames()[0];
        String[] returnName = new String[1];
        Query query = new Query();
        returnName[0] = "SnodeID as dd";
        query.setPropertyNames(returnName);
        query.setTypeName(typeName);

        FeatureReader<SimpleFeatureType, SimpleFeature> featureSource = dataStore.getFeatureReader(query, Transaction.AUTO_COMMIT);
        featureSource.getFeatureType().getTypes().forEach(attributeType -> {
            System.out.println(attributeType.getName());
        });
    }
}
