package spring.study.conditional.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.system.JavaVersion;
import org.springframework.stereotype.Component;


@ConditionalOnJava(JavaVersion.NINE)
@Component
public class ConditionBeanWithComponent {
}
