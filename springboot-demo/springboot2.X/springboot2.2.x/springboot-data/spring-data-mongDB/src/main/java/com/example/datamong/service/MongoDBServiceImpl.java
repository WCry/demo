package com.example.datamong.service;

import com.example.datamong.FileInfo;
import com.example.datamong.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class MongoDBServiceImpl implements DataService {
    @Autowired
    public FileRepository fileRepository;

    @Override
    public byte[] findDataByPath(String path) {
        return fileRepository.findByPath(path).getContent().getData();
    }

    @Override
    public void saveData(FileInfo fileInfo) {
        fileRepository.save(fileInfo);
    }
}
