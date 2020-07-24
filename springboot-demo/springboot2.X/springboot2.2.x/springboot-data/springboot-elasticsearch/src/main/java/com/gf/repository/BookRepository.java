package com.gf.repository;


import com.gf.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface BookRepository extends ElasticsearchRepository<Book, Integer>{

    List<Book> findByBookNameLike(String bookName);
    //不能构建sql查询条件
//    @Query(value = "select name,author,price from Book b where b.name like %:name%")
//    List<Book> findByNameMatch(@Param("name") String name);
}
