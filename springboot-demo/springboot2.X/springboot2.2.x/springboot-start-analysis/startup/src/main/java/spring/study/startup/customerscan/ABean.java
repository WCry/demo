package spring.study.startup.customerscan;

import org.springframework.stereotype.Component;

/**
 * @author zhangxuepei
 * @since 3.0
 */
@Component
public class ABean {
    public ABean(MetaBean1 metaBean1){
        System.out.println("metaBean"+metaBean1);
    }
}
