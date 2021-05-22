package com.example.datamong;

import org.bson.types.Binary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class DatamongApplicationTests {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Test
    void contextLoads() throws IOException {
        // fileRepository.deleteAll();
//        File file = new File("C:\\Users\\zhangxuepei\\Desktop\\SpringbootDataES\\dsdsa.png");
//        String path = "/11/122222/11223";
//        FileInfo fileInfo = new FileInfo();
//        fileInfo.setPath(path);
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes);
//        Binary binary = new Binary(bytes);
//        fileInfo.setContent(binary);
//        fileInfo.setSize(inputStream.available());
//        fileInfo.setContentType(".png");
//        fileRepository.save(fileInfo);
//
//        File file2 = new File("C:\\Users\\zhangxuepei\\Desktop\\SpringbootDataES\\dsdsa.png");
//        String path2 = "/12/122222/11223";
//        FileInfo fileInfo2 = new FileInfo();
//        fileInfo2.setPath(path2);
//        FileInputStream inputStream2 = new FileInputStream(file2);
//        byte[] bytes2 = new byte[inputStream2.available()];
//        inputStream2.read(bytes2);
//        Binary binary2 = new Binary(bytes2);
//        fileInfo2.setContent(binary2);
//        fileInfo2.setSize(inputStream2.available());
//        fileInfo2.setContentType(".png");
//        fileRepository.save(fileInfo2);
        fileRepository.count();
        List<FileInfo> dasddas = fileRepository.findAll();
       // dasddas.forEach(v->{System.out.println(v.getPath());});
        System.out.println(dasddas.size());
        // FileInfo dasd = fileRepository.findByPath(path2);

    }
}
