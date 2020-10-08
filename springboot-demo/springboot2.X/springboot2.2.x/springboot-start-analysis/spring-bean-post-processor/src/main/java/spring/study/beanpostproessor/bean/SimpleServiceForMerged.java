package spring.study.beanpostproessor.bean;

import org.springframework.stereotype.Component;


@Component
public class SimpleServiceForMerged {
    public SimpleServiceForMerged() {
        System.err.println("SimpleServiceForMerged constructor default");
    }
}
