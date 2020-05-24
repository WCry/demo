package com.example.springbootes.demo.blocg;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EsBlogRepository3 extends ElasticsearchRepository<EsBlog3, String> {
    List<EsBlog> findDistinctByEsBlogtttt_Content(String content);
    /**
     * 分页查询博客，去重
     */
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);

    EsBlog queryEsBlogByContent(String content);

    void streamDistinctByContent(String content);
}