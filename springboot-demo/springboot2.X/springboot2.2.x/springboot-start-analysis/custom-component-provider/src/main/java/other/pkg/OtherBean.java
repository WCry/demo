package other.pkg;

import org.springframework.stereotype.Component;

@Component
public class OtherBean {
    public OtherBean(){
        System.out.println("注解@Component 需要格外扫描才能够加载");
    }
}
