package spring.study.refresh.context.bean;

import org.springframework.context.annotation.Bean;

/**
 * 注入一个服务类
 *  通过@Import() 在启动类上进行注入
 *   同时可以使用
 * @Compoent 注解
 *  等其他类的注入方式
 */
public class ImportService {
    /**
     * 注解一个Bean对象
     * @return
     */
    @Bean
    public SimpleServiceInImportService simpleServiceInImportService() {
        System.out.println("dsad");
        return new SimpleServiceInImportService();
    }

}
