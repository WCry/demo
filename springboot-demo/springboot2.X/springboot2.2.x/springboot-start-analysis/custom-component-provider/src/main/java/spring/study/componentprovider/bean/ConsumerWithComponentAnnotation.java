package spring.study.componentprovider.bean;

import org.springframework.stereotype.Component;
import spring.study.componentprovider.annotation.Consumer;

/**
 * 和ConsumerRegistrar中不一样的注解，也将不会被扫描到
 */
@Component
public class ConsumerWithComponentAnnotation {
}
