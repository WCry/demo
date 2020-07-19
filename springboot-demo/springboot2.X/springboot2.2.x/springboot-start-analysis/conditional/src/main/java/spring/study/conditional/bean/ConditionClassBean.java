package spring.study.conditional.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.stereotype.Component;


@ConditionalOnBean(value = FreeMarkerAutoConfiguration.class)
@Component
public class ConditionClassBean {
}
