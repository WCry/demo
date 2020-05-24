package com.lhj.miaosha.redis;

/**
 * @author ：LHJ
 * @date ：2019/7/25 23:11
 * @description：${description}
 */
public class OrderKey extends BasePrefix {

    public OrderKey(String prefix) {
        super(prefix);
    }
    public static OrderKey getMiaoshaOrderByUidGid = new OrderKey("moug");
}
