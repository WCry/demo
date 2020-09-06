package com.zxp.goods.controller;


import com.zxp.goods.params.GoodsPreOccupationDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/goods/preOccupation")
public class GoodsController {

    public GoodsController() {
    }

    /**
     * 商品预占，下订单，但是未付款
     */
    @RequestMapping(value = "/try")
    @Transactional
    public String tryGoodsPreOccupation(GoodsPreOccupationDTO goodsPreOccupationDTO) {
    }
}
