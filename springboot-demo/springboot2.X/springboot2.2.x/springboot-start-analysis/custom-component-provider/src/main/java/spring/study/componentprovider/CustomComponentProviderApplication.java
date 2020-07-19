package spring.study.componentprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import other.pkg.OtherBean;
import spring.study.componentprovider.annotation.EnableConsumer;


@SpringBootApplication
@EnableConsumer
@ImportResource(locations={"classpath:beans.xml"})
public class CustomComponentProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomComponentProviderApplication.class, args);
    }

}
