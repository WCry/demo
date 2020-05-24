package com.example.springbootes.demo.blocg;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * 这里Type设置成为_doc 默认的是
 * 因为7不在支持其他type的设定，8.0不在支持type的设定
 */
@Document(indexName = "blog", type = "_doc")
public class EsBlog implements Serializable {
    private static final long serialVersionUID = -2210631390635355780L;
    @Id
    private String id;
    // 建立索引时不使用分词
    @Field(type = FieldType.Keyword)
    private String title;
    // 摘要
    @Field(type = FieldType.Keyword)
    private String summary;
    @Field(type = FieldType.Keyword)
    private String content;
    @Field(type = FieldType.Nested)
    private EsBlogtttt esBlogtttt;

    public EsBlogtttt getEsBlogtttt() {
        return esBlogtttt;
    }

    public void setEsBlogtttt(EsBlogtttt esBlogtttt) {
        this.esBlogtttt = esBlogtttt;
    }

    /**
     * JPA规范要求，防止直接使用
     */
    protected EsBlog(){}

    public EsBlog(String title, String summary, String content) {
        this.title = title;
        this.summary = summary;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "EsBlog{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}