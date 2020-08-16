package com.zxp.api.goods.feign;

import com.zxp.api.goods.pojo.Category;
import com.zxp.resoponse.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



public interface CategoryFeignAPI {
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable(name = "id") Integer id);
}
