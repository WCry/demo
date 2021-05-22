package com.example.datamong;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zhangxuepei
 * @since 3.0
 */


@Document
@Data
public class FileInfo {
    @Id
    private String id;
    private String path; // 文件名
    private Binary content; // 文件内容
    private String contentType; // 文件类型
    private long size; // 文件大小
}
