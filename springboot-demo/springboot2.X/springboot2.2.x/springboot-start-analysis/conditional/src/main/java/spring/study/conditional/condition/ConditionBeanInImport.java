package spring.study.conditional.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.stereotype.Component;

/**
 *
 */
@ConditionalOnJava(ConditionalOnJava.JavaVersion.NINE)
public class ConditionBeanInImport {


    public SimpleBean simpleBean() {
        return new SimpleBean();
    }

    class SimpleBean {

    }

}
