package spring.study.beanpostproessor.embedded;

import org.springframework.beans.factory.annotation.Required;


//@Component
public class BeanForRequired {

    public BeanForRequired() {
        System.err.println("constructor BeanForRequired");
    }

    private RequiredNestedBean nestedBean;

    @Required
    public void setNestedBean(RequiredNestedBean nestedBean) {
        this.nestedBean = nestedBean;
    }
}


//@Component
class RequiredNestedBean {
    public RequiredNestedBean() {
        System.err.println("constructor RequiredNestedBean");
    }
}