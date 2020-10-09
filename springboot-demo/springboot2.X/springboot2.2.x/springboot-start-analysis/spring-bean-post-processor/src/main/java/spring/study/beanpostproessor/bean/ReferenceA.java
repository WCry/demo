package spring.study.beanpostproessor.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ReferenceA {
    @Autowired
    private ReferenceB referenceB;
    public ReferenceA() { }
}
