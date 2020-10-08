package spring.study.startup.customerscan;

import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class NormalBean {
    public NormalBean(){
        System.out.println("Normal Bean");
    }
}
