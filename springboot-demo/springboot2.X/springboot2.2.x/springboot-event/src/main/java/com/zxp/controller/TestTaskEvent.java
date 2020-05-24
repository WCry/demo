package com.zxp.controller;

import org.springframework.context.ApplicationEvent;

/**
 * user:zxp
 * Day:2020,05,23
 **/
public class TestTaskEvent extends ApplicationEvent {

    public TestTaskEvent(Object source) {
        super(source);
    }

    private String taskID;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }
}
