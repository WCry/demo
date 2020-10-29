package com.geotoolstest;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestGeotoolHBase {
    private static String catalog = "China";
    //集群
    private static String zookeepers = "172.16.18.8:2181";

    public static void main(String[] args) {
        try {
            DataStore dataStore = DataStoreFinder.getDataStore(getParams());
            if (dataStore != null) {
                //获取Catalog下所有的数据表
                String[] typeNames = dataStore.getTypeNames();
                if (typeNames.length > 0) {
                    //获取第一张数据表的数据信息
                    SimpleFeatureSource featureSource = dataStore.getFeatureSource(typeNames[0]);
                    if (featureSource != null) {
                        //获取该数据表的属性信息
                        SimpleFeatureType featureType = featureSource.getSchema();
                        System.out.println("表名:" + featureType.getTypeName());
                        System.out.println("字段数:" + featureType.getAttributeCount());
                        //获取数据表的属性(字段)结构
                        List<AttributeDescriptor> attributeDescriptors = featureType.getAttributeDescriptors();
                        for (AttributeDescriptor attributeDescriptor : attributeDescriptors) {
                            System.out.println("字段名:" + attributeDescriptor.getLocalName());
                        }
                        //获取数据表的记录信息
                        SimpleFeatureCollection features = featureSource.getFeatures();
                        SimpleFeatureIterator featureIterator = features.features();
                        //打印前10条记录
                        int counter = 0;
                        while (featureIterator.hasNext()) {
                            //一条记录集
                            SimpleFeature simpleFeature = featureIterator.next();
                            //获取记录集信息
                            Collection<Property> properties = simpleFeature.getProperties();
                            Iterator<Property> iterator = properties.iterator();
                            while (iterator.hasNext()) {
                                Property property = iterator.next();
                                System.out.println(property.getName().getLocalPart() + ":" + property.getValue().toString());
                            }
                            if (counter > 10) {
                                break;
                            }
                            counter++;
                        }

                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Map getParams() {
        Map params = new HashMap();
        params.put("hbase.zookeepers", zookeepers);
        params.put("hbase.catalog", catalog);
        return params;
    }
}
