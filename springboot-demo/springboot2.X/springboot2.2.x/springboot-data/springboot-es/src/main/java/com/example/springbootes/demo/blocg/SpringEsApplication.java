package com.example.springbootes.demo.blocg;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter;
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext;

@SpringBootApplication
public class SpringEsApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.example.springes.SpringEsApplication.class, args);
    }
    @Bean
    ResultsMapper resultsMapper(SimpleElasticsearchMappingContext mappingContext, EntityMapper entityMapper) {
        return new DefaultResultMapper(mappingContext, entityMapper);
    }
    @Bean
    MyElasticsearchRestTemplate elasticsearchTemplate(RestHighLevelClient client, ElasticsearchConverter converter, ResultsMapper resultsMapper) {
        return new MyElasticsearchRestTemplate(client, converter, resultsMapper);
    }
}
