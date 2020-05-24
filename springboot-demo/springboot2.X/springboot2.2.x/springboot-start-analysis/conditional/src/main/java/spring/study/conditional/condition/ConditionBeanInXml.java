package spring.study.conditional.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.stereotype.Component;

/**
 * Conditional有条件的
 *             条件成立  java版本 需要java版本9
 */
@ConditionalOnJava(ConditionalOnJava.JavaVersion.NINE)
public class ConditionBeanInXml {
}
