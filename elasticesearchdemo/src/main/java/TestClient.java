import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestClient {
    public  static RestHighLevelClient restHighLevelClient;
    public static void main(String[] args) throws IOException {
        restHighLevelClient= new RestHighLevelClient(RestClient.builder(new HttpHost("10.19.154.149", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest("twitter","pofdssts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse=restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        System.out.println(searchResponse.getHits().getHits().length);

        ClusterHealthResponse clusterHealthResponse= restHighLevelClient.cluster().
                health(new ClusterHealthRequest(), RequestOptions.DEFAULT);
        System.out.println(clusterHealthResponse.getStatus());
        System.out.println(clusterHealthResponse.getClusterName());
        System.out.println(clusterHealthResponse.getNumberOfDataNodes());
        //表名twitter
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("twitter");
        try {
            createIndexRequest.mapping(
                    "{\n" +
                            "  \"properties\": {\n" +
                            "    \"user\": {\n" +
                            "      \"type\": \"text\"\n" +
                            "    }\n" +
                            "}\n" +
                            "}",
                    XContentType.JSON);
          //  CreateIndexResponse createIndexResponse=restHighLevelClient.indices().create(createIndexRequest,RequestOptions.DEFAULT);
            System.out.println("创建成功");
            restHighLevelClient.indices().get(new GetIndexRequest("twitter"),RequestOptions.DEFAULT);
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("user", "kimchy");
            jsonMap.put("postDate", new Date());
            jsonMap.put("message", "trying out Elasticsearch");
            IndexRequest indexRequest = new IndexRequest("pofdssts").id("1").source(jsonMap);
            restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);
            jsonMap = new HashMap<>();
            jsonMap.put("user", "kimchy");
            jsonMap.put("postDate", new Date());
            jsonMap.put("message", "trying out Elasticsearch");
            indexRequest = new IndexRequest("posfdsfts").id("2").source(jsonMap);
            restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);
            jsonMap = new HashMap<>();
            jsonMap.put("user", "kimchy");
            jsonMap.put("postDate", new Date());
            jsonMap.put("message", "trying out Elasticsearcdasdassdh");
            indexRequest = new IndexRequest("pfdsfosts").id("3").source(jsonMap);
            restHighLevelClient.index(indexRequest,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            DeleteIndexRequest request = new DeleteIndexRequest("twitter");
//            restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
//            System.out.println("删除");
//        } catch (ElasticsearchException exception) {
//            if (exception.status() == RestStatus.NOT_FOUND) {
//                System.out.println("未发现");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        System.exit(0);
    }
}
