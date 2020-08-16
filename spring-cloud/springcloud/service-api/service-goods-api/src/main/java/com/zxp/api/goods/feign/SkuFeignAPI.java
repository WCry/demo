package com.zxp.api.goods.feign;

import com.changgou.order.pojo.OrderItem;
import com.zxp.api.goods.pojo.Sku;
import com.zxp.resoponse.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;



public interface SkuFeignAPI {
    /**
     * 查询符合条件的状态的SKU的列表
     * @param status
     * @return
     */
    @GetMapping("/status/{status}")
    public Result<List<Sku>> findByStatus(@PathVariable(name="status") String status);


    /**
     * 根据条件搜索的SKU的列表
     * @param sku
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false) Sku sku);


    @GetMapping("/{id}")
    public Result<Sku> findById(@PathVariable(name="id") Long id);



    @PostMapping(value = "/decr/count")
    public Result decrCount(@RequestBody OrderItem orderItem);



}
