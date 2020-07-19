package spring.study.conditional.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.stereotype.Component;


@ConditionalOnResource(resources = "mybatis.xml")
@Component
public class ConditionResourceBean {
}
