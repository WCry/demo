package com.zxp.api.feign;
import com.zxp.api.pojo.Content;

import com.zxp.resoponse.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public interface ContentFeignAPI {
    /*
    根据分类的ID 获取到广告列表
     */
    @GetMapping(value = "/list/category/{id}")
    Result<List<Content>> findByCategory(@PathVariable(name="id") Long id);
}