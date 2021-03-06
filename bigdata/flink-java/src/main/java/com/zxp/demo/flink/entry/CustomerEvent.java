package com.zxp.demo.flink.entry;

import java.util.Date;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class CustomerEvent {
    private String name;

    /**
     * 事件类型
     */
    private int type;

    /**
     * 时间戳
     */
    private long timestamp;


    private Date date;


    public CustomerEvent() {
    }


    public CustomerEvent(String name, int type, long timestamp) {
        this.name = name;
        this.type = type;
        this.timestamp = timestamp;
    }

    public CustomerEvent(String name, int type, long timestamp, Date date) {
        this.name = name;
        this.type = type;
        this.timestamp = timestamp;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", timestamp=" + timestamp +
                ", date=" + date +
                '}';
    }
}
