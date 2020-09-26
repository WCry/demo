package com.zxp.goods.controller;


import com.zxp.goods.params.GoodsPreOccupationDTO;
import com.zxp.goods.service.GoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/goods")
public class GoodsController {
    private GoodsService goodsService;
    public GoodsController(GoodsService goodsService) {
        this.goodsService=goodsService;
    }

    /**
     * 商品预占，下订单，但是未付款
     */
    @RequestMapping(value = "/preOccupation")

    public String goodsPreOccupation(GoodsPreOccupationDTO goodsPreOccupationDTO) {
        this.goodsService.goodsPreOccupation(goodsPreOccupationDTO);
        return UUID.randomUUID().toString();
    }
}
