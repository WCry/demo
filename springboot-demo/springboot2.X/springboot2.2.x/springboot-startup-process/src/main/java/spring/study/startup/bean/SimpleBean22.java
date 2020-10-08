package spring.study.startup.bean;

import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * Created by Format on 2017/5/1.
 */
//Lombok注解参数

@ToString
//Spring注解参数  注册一个组成
@Component
public class SimpleBean22 {
    public String id;
    public String name;
    public SimpleBean22() {
        System.out.println("kong222初始化了");
    }
    public SimpleBean22(String id, String name) {
        this.id = id;
        this.name = name;
        System.out.println("初始化了");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
