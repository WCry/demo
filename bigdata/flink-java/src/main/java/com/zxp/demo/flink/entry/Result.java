package com.zxp.demo.flink.entry;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public  class Result {
    public String player;
    public Long num;

    public Result() {
        super();
    }
    public Result(String player, Long num) {
        this.player = player;
        this.num = num;
    }
    @Override
    public String toString() {
        return player + ":" + num;
    }
}
