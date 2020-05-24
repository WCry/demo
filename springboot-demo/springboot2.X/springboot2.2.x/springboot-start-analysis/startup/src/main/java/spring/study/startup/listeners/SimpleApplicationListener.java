package spring.study.startup.listeners;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * 创建应用程序启动监听事件
 * 创建Spring启动的监听事件 监听应用程序事件
 */
public class SimpleApplicationListener implements ApplicationListener<ApplicationEvent> {
   public SimpleApplicationListener(){
       System.out.println("dsads");
   }
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
       //可以获取参数 启动 环境变量的参数
        //可以获取SpringApplication
        if(event instanceof ApplicationStartingEvent) {
            //Spring 程序启动事件  这个事件比较早
            // 启动最先完成
            //在启动Web容器和加载配置之前
            System.out.println("我要监听 ApplicationListener的ApplicationStartingEvent 最先初始化 ");
            System.out.println("===== custom started event in initializer");
        } else if(event instanceof ApplicationReadyEvent) {
            //准备就绪 可以获取SpringApplicationContext事件
            //Spring 程序已经准备就绪
            System.out.println("我要监听ApplicationListener的ApplicationReadyEvent代表初始化完成 ");
            System.out.println("===== custom ready event in initializer");
        }
    }
}