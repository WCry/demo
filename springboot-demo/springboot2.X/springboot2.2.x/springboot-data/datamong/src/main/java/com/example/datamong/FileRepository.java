package com.example.datamong;

import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public interface FileRepository extends MongoRepository<FileInfo, String> {
    FileInfo findByPath(String name);

}
