package com.lhj.miaosha.dao;

import com.lhj.miaosha.domain.MiaoshaOrder;
import com.lhj.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {


    @Select("select id as 'id',user_id as 'userId',order_id as 'orderId',goods_id as 'goodsId' from miaosha_order where user_id = #{userId} and goods_id = #{goodsId}")
     MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

    @Insert("insert into miaosha_order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = long.class, before = false, statement = "select last_insert_id()")
     long insert(OrderInfo orderInfo);


    @Insert("insert into miaosha_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
     int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

    @Select("select id as 'id',user_id as 'userId',goods_id as 'goodsId',delivery_addr_id as 'deliveryAddrId', " +
            "goods_name as 'goodsName',goods_count as 'goodsCount',goods_price as 'goodsPrice'," +
            "order_channel as 'orderChannel',status as 'status',create_date as 'createDate'," +
            "pay_date as 'payDate' from miaosha_order_info where id = #{orderId}")
     OrderInfo getOrderById(@Param("orderId")long orderId);
}
