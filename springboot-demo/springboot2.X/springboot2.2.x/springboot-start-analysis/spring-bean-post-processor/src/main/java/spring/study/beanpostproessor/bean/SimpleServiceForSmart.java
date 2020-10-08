package spring.study.beanpostproessor.bean;

import org.springframework.stereotype.Component;


@Component
public class SimpleServiceForSmart {
    private EmbeddedService embeddedService;
    public SimpleServiceForSmart() {
        System.err.println("SimpleServiceForSmart constructor default");
    }
    public SimpleServiceForSmart(EmbeddedService embeddedService) {
        this.embeddedService = embeddedService;
        System.err.println("SimpleServiceForSmart constructor embeddedService: " + embeddedService);
    }
}
