package com.zxp.goods.feign;

import com.zxp.user.resoponse.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface CategoryFeignAPI {
    @GetMapping("/{id}")
    public Result<Category> findById(@PathVariable(name = "id") Integer id);
}
