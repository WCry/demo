package spring.study.property.loader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringEnvironmentPropertySourceApplication {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication();
        sa.run(SpringEnvironmentPropertySourceApplication.class, args);
    }

}
