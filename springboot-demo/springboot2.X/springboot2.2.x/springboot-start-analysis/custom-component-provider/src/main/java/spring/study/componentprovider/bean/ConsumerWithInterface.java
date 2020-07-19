package spring.study.componentprovider.bean;


import spring.study.componentprovider.interfaze.IConsumer;

/**
 *
 */
public class ConsumerWithInterface implements IConsumer {
    public ConsumerWithInterface(){
        System.out.println(" 通过自定义注解实现注入Bean，ConsumerWithInterface implements IConsumer");
    }
}
