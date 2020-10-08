package spring.study.beanpostproessor.bean;

import org.springframework.stereotype.Component;


@Component
public class SimpleServiceForDestroy {
    public SimpleServiceForDestroy() {
        System.err.println("SimpleServiceForDestroy constructor default");
    }
}
