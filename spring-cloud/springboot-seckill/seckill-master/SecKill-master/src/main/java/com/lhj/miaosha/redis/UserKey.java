package com.lhj.miaosha.redis;

/**
 * @author ：LHJ
 * @date ：2019/7/23 18:58
 * @description：${description}
 */
public class UserKey extends BasePrefix {

    public UserKey(String prefix) {
        super(prefix);
    }
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
}
