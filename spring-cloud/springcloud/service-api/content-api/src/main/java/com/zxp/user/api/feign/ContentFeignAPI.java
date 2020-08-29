package com.zxp.user.api.feign;
import com.zxp.user.api.pojo.Content;

import com.zxp.user.resoponse.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public interface ContentFeignAPI {
    /*
    根据分类的ID 获取到广告列表
     */
    @GetMapping(value = "/list/category/{id}")
    Result<List<Content>> findByCategory(@PathVariable(name="id") Long id);
}