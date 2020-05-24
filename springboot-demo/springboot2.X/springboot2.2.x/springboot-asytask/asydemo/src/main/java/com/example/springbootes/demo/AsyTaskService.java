package com.example.springbootes.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhangxuepei
 */
@Component
public class AsyTaskService {
    private HashMap<String, Future<String>> resultMap = new HashMap<>();
    @Autowired
    private ApplicationContext applicationContext;

    public HashMap<String, Future<String>> getResultMap() {
        return resultMap;
    }

    public void setResultMap(HashMap<String, Future<String>> resultMap) {
        this.resultMap = resultMap;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public String startAsyDoing() {
        String uuID = UUID.randomUUID().toString();
        //todo: 需要注意点   注意了  注意了 注意了  注意了注意了  注意了注意了  注意了
        //这里一点需要非常注意，因为异步处理使用的是AOP方式实现，所以这里需要使用上下文获取到代理之后
        //在进行操作，才能实现异步操作。获取异步结果
        AsyTaskService asyTaskService = applicationContext.getBean(AsyTaskService.class);
        asyTaskService.getResultMap().put(uuID, asyTaskService.startAsyDoing(uuID));
        return uuID;
    }

    public String queryResult(String id) {
        AsyTaskService asyTaskService = applicationContext.getBean(AsyTaskService.class);
        Future<String> task = asyTaskService.getResultMap().get(id);
        if (task != null) {
            if (task.isDone()) {
                try {
                    //查询任务结果
                    String taskResult = resultMap.get(id).get();
                    //查询完成移出任务
                    resultMap.remove(id);
                    return taskResult;

                } catch (ExecutionException | InterruptedException e) {
                    return "查询错误";
                }
            }
            return "正在执行";
        } else {
            return "没有查询到";
        }
    }


    @Async
    public Future<String> startAsyDoing(String dd) {
        try {
            Thread.sleep(10000);
            return new AsyncResult<>(dd);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
