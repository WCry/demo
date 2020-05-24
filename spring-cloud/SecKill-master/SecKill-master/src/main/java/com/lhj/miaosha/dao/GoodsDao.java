package com.lhj.miaosha.dao;

import com.lhj.miaosha.domain.MiaoshaGoods;
import com.lhj.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsDao {
    @Select("select g.id as 'id',g.goods_name as  'goodsName',g.goods_title as 'goodsTitle'," +
            "g.goods_img as 'goodsImg',g.goods_detail as 'goodsDetail',g.goods_price as 'goodsPrice'," +
            "g.goods_stock as 'goodsStock',sg.stock_count as 'stockCount', sg.start_date as 'startDate', sg.end_date as 'endDate', " +
            "sg.seckill_price as 'miaoshaPrice'" +
            "from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id")
     List<GoodsVo> listGoodsVo();

    @Select("select g.id as 'id',g.goods_name as 'goodsName',g.goods_title as 'goodsTitle'," +
            "g.goods_img as 'goodsImg',g.goods_detail as 'goodsDetail',g.goods_price as 'goodsPrice'," +
            "g.goods_stock as 'goodsStock',sg.seckill_price as 'miaoshaPrice'," +
            "sg.stock_count as 'stockCount',sg.start_date as 'startDate'," +
            "sg.end_date as 'endDate'"+
            "from sk_goods_seckill sg left join sk_goods g  on sg.goods_id = g.id where g.id = #{goodsId,jdbcType=BIGINT}")
     GoodsVo getGoodsVoByGoodsId(@Param("goodsId") Integer goodsId);

    @Update("update sk_goods_seckill set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count > 0 ")
     int  reduceStock(MiaoshaGoods g);
}
