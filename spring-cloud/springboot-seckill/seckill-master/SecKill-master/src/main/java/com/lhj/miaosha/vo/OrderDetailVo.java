package com.lhj.miaosha.vo;

import com.lhj.miaosha.domain.OrderInfo;

/**
 * @author ：LHJ
 * @date ：2019/7/29 9:44
 * @description：${description}
 */
public class OrderDetailVo {
    private GoodsVo goods;
    private OrderInfo order;
    public GoodsVo getGoods() {
        return goods;
    }
    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }
    public OrderInfo getOrder() {
        return order;
    }
    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
