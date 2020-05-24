package com.example.springbootes.demo.book;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

// SpringBoot启动时会自动创建映射，但要注意如果已经存在相同的index，必须先删除
//@Document(indexName = "#{esMyConfig.indexName}",type="#{esMyConfig.typeName}")
@Document(indexName = "bookstore", type = "bookentity")
public class BookStore implements Serializable {
    @Id
    private String id;
    @Field(type = FieldType.Integer)
    private List<Integer> idsss;
    @Field(type = FieldType.Nested)
    private Book book;

    public BookStore() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Integer> getIdsss() {
        return idsss;
    }

    public void setIdsss(List<Integer> idsss) {
        this.idsss = idsss;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
