package com.lhj.miaosha.redis;

/**
 * @author ：LHJ
 * @date ：2019/7/31 11:00
 * @description：${description}
 */
public class MiaoshaKey extends BasePrefix {


    private MiaoshaKey(int expireSeconds,String prefix) {
        super( expireSeconds,prefix);
    }

    public static MiaoshaKey isGoodsOver = new MiaoshaKey(0,"go");
    public static MiaoshaKey getMiaoshaPath = new MiaoshaKey(60, "mp");
    public static MiaoshaKey getMiaoshaVerifyCode = new MiaoshaKey(300, "vc");

}