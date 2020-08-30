package com.zxp.content.feign;

import com.zxp.content.pojo.Content;
import com.zxp.user.resoponse.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


public interface ContentFeignAPI {
    /*
    根据分类的ID 获取到广告列表
     */
    @GetMapping(value = "/list/category/{id}")
    Result<List<Content>> findByCategory(@PathVariable(name="id") Long id);
}