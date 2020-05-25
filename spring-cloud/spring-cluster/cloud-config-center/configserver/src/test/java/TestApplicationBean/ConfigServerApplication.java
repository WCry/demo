package TestApplicationBean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableConfigServer
@Configuration
public class ConfigServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context=SpringApplication.run(ConfigServerApplication.class, args);
        String[] names= context.getBeanNamesForType(Tes.class);
        System.out.println(names.length);
        System.out.println();
        System.out.println(names[1]);
        Tes tes1= (Tes) context.getBean(names[0]);
        tes1.sy();
        Tes tes2= (Tes) context.getBean(names[1]);
        tes2.sy();
    }
    @Bean
    public Tesa helloService(){
        return new Tesa();
    }
    @Bean
    public testClas hello(){
        return new testClas();
    }
}
