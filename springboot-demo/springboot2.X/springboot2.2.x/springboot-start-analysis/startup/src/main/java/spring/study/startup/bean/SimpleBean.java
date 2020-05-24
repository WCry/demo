package spring.study.startup.bean;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Format on 2017/5/1.
 */
//Lombok注解参数

@ToString
//Spring注解参数  注册一个组成
//Spring注解参数  注册一个组件
@Component
public class SimpleBean {
    public String id;
    public String name;

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
    public SimpleBean(){
        System.out.println("我是 Component 注册过来的 SimpleBean 空方法初始化");
    }
    public SimpleBean(String id,String name){
        this.name=name;
        this.id=id;
        System.out.println(String.format("SimpleBean id：{%s}和name：{%s}初始化",id,name));
    }
}
