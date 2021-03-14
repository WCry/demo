package com.jesper.seckill.rabbitmq;

import com.jesper.seckill.bean.User;

public class SeckillMessage {

    private User user;
    private long goodsId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
