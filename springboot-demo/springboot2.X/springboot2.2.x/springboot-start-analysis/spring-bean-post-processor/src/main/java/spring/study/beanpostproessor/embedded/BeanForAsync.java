package spring.study.beanpostproessor.embedded;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;



@Component
public class BeanForAsync {

    @Async
    public void async() {
        try {
            Thread.sleep(5000l);
            System.out.println("async over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
