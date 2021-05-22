package com.example.datamong.service;

import com.example.datamong.FileInfo;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public interface DataService {
    byte[] findDataByPath(String path);
    void saveData(FileInfo fileInfo);
}
