package com.example.springbootes.demo.book;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 继承数据库操作类
 */
@Repository
public interface BookRepository extends ElasticsearchRepository<BookStore,String> {
    List<BookStore> findByBook_Title(String title);
}
