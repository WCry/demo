package spring.study.componentprovider.bean;


import spring.study.componentprovider.interfaze.IProducer;

/**
 *  实现IProducer 和ConsumerRegistrar
 *  需要自动注解的类不一致，将不会被扫描到
 */
public class ProducerWithInterface implements IProducer {
}
