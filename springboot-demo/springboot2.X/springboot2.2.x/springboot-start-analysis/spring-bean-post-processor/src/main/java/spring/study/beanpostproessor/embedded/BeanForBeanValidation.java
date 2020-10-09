package spring.study.beanpostproessor.embedded;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


//@Component
public class BeanForBeanValidation {

    public BeanForBeanValidation() {
        System.err.println("constructor BeanForBeanValidation");
    }

    @NotNull
    private String id;

    @Min(value = 10)
    private int age;

}

