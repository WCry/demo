**springboot使用热部署**

springboot使用热部署关键点

1、在pom.xml文件中添加依赖

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <optional>true</optional>
    </dependency>
       <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <excludeDevtools>false</excludeDevtools>
                </configuration>
            </plugin>
        </plugins>
```
   properties文件中配置：

```yaml
spring:
  devtools:
    restart:
      enabled: false
    remote:
      secret: 123456
```

2、开启IDEA对热部署的支持
    (1)、Settings --> Build，Execution，Deployment --> 勾选Build project automatically
    <img src="/images/Build project automatically.png" width="500px">
    (2)、按快捷键Ctrl + Shift + Alt + /，选择Registry，勾选compiler.automake.allow.when.app.running
    <img src="/images/Registry.png" width="500px">
    <img src="/images/compiler.automake.allow.when.app.running.png" width="500px">

3.远程调试

​    ![](/images/7.png)

```
添加Springboot的远程启动主类
org.springframework.boot.devtools.RemoteSpringApplication;
```

4.本地改变代码

运行DevtoolsApplication的配置，ctrl+shift+F9编译代码将本地改变热部署到远程

参考博客：

https://blog.csdn.net/u010588262/article/details/86999228?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param