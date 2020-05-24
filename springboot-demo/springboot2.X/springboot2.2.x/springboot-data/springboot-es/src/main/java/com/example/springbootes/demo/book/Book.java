package com.example.springbootes.demo.book;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

public class Book implements Serializable {
    @Field(type = FieldType.Text, includeInParent = true)
    private String title;
    @Field(type = FieldType.Keyword, includeInParent = true)
    private String author;

    public Book() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
