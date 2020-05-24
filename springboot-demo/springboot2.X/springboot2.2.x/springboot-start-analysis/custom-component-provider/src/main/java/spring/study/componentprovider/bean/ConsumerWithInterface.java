package spring.study.componentprovider.bean;


import spring.study.componentprovider.interfaze.IConsumer;

/**
 *
 */
public class ConsumerWithInterface implements IConsumer {
    public ConsumerWithInterface(){
        System.out.println("ConsumerWithInterface implements IConsumer");
    }
}
