package com.lhj.miaosha.redis;

public interface KeyPrefix {

     int expireSeconds();

     String getPrefix();

}
