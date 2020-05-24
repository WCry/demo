//import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
//import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
//import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
//import org.elasticsearch.action.delete.DeleteResponse;
//import org.elasticsearch.action.get.GetResponse;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.index.IndexResponse;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.Date;
//import java.util.concurrent.ExecutionException;
//
//import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
//
//
//
//public class ElasticSearch {
//
//    //private Logger logger
//    public final static String HOST = "10.33.42.181";
//    public final static int PORT = 9300;
//    public final static String _cluster_name = "my_ElasticcSearch_10";
//    public final static String _index = "es1024";
//
//    public final static String _type = "t_link";
//    public Client client = null;
//
//    public ElasticSearch() {
//
//    }
//
//    public void openConnectES() throws UnknownHostException {
//        Settings settings = Settings.settingsBuilder()
//                .put("cluster.name", _cluster_name).build();
//        client = TransportClient.builder().settings(settings).build()
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT));
//    }
//
//    public void closeConnectES() {
//        client.close();
//    }
//
//    //创建索引index--类似于数据库
//    public void createIndex() throws IOException {
//        XContentBuilder mapping = XContentFactory.jsonBuilder()
//                .startObject()
//                .startObject("settings")
//                .field("number_of_shards", 2)    //分片数量
//                .field("number_of_replicas", 0)    //副本数量
//                .endObject()
//                .endObject()
//                .startObject()
//                .startObject("t_type")    //表名称
//                .startObject("properties")    //列属性
//                .startObject("type").field("type", "string").field("store", "yes")
//                .endObject()
//                .startObject("eventCount").field("type", "long").field("store", "yes")
//                .endObject()
//                .startObject("eventDate").field("type", "date")
//                .field("format", "dateOptionalTime").field("stroe", "yes")
//                .endObject()
//                .startObject("message").field("type", "string")
//                .field("index", "not_analyzed").field("stroe", "yes")
//                .endObject()
//                .endObject()
//                .endObject()
//                .endObject();
//        CreateIndexRequestBuilder cirb = client.admin().indices()
//                .prepareCreate("i_index")
//                .setSource(mapping);
//        CreateIndexResponse response = cirb.execute().actionGet();
//        if (response.isAcknowledged()) {
//            System.out.println("Index created.");
//        } else {
//            System.err.println("Index creation failed.");
//        }
//
//    }
//
//    public void insert() throws IOException {
//        IndexResponse response = client
//                .prepareIndex("i_index", "t_type", "1")
//                .setSource(
//                        jsonBuilder().startObject()
//                                .field("type", "syslog")
//                                .field("eventCount", 1)
//                                .field("eventDate", new Date())
//                                .field("message", "i_index insert doc test")
//                                .endObject()).get();
//        System.out.println("index" + response.getIndex()
//                + " insert doc id:" + response.getId()
//                + " result:" + response.isCreated());
//    }
//
//    public void update() throws IOException, InterruptedException, ExecutionException {
//        UpdateRequest updateRequest = new UpdateRequest();
//        updateRequest.index("i_index");
//        updateRequest.type("t_type");
//        updateRequest.id("1");
//        updateRequest.doc(
//                jsonBuilder()
//                        .startObject().field("type", "file").endObject());
//        client.update(updateRequest).get();
//    }
//
//    public void updateIfNoExist() throws IOException, InterruptedException, ExecutionException {
//        IndexRequest indexRequest = new IndexRequest("i_index", "t_type", "100")
//                .source(jsonBuilder()
//                        .startObject()
//                        .field("type", "syslog")
//                        .field("eventCount", 2)
//                        .field("eventDate", new Date())
//                        .field("message", "i_index insert doc test")
//                        .endObject());
//        UpdateRequest updateRequest = new UpdateRequest("i_index", "t_type", "100")
//                .doc(jsonBuilder().startObject().field("type", "file").endObject())
//                .upsert(indexRequest);    //upsert:更新插入
//        client.update(updateRequest).get();
//    }
//
//    public void delete() {
//        //删除文档
//        DeleteResponse deleteReponse = client.prepareDelete("i_index", "t_type", "100").get();
//        boolean isFound = deleteReponse.isFound();
//
//        //删除索引
//        DeleteIndexRequest delete = new DeleteIndexRequest("i_index");
//        client.admin().indices().delete(delete);
//    }
//
//    public void query() throws UnknownHostException {
//        GetResponse response = client.prepareGet(_index, _type, "11056").get();
//        String source = response.getSource().toString();
//        long version = response.getVersion();
//        String indexName = response.getIndex();
//        String type = response.getType();
//        String id = response.getId();
//
///*
//		Settings settings = Settings.settingsBuilder().put("cluster.name", "my_ElasticcSearch_10").build();
//
//		try {
//
//			Client client = TransportClient.builder().settings(settings).build()
//					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
//
//			// 批量创建索引
//			BulkRequestBuilder bulkRequest = client.prepareBulk();
//			Map<String, Object> map = new HashMap<>();
//			map.put("link", "Jack");
//
//			IndexRequest request = client.prepareIndex("es1024", "t_link").setSource(map).request();
//			bulkRequest.add(request);
//			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
//			if (bulkResponse.hasFailures()) {
//				System.out.println("批量创建索引错误！");
//			}
//
//			GetResponse response = client.prepareGet("es1024", "t_link", "11056").get();
//			String source = response.getSource().toString();
//
//			client.close();
//			System.out.println("批量创建索引成功");
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//*/
//    }
//}