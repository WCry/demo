package spring.study.conditional.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;


@ConditionalOnExpression("'${server.host}' == 'localhost'")
@Component
public class ConditionExpressionBean {
}
