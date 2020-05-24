package spring.study.startup.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 应用程序的命令行运行处理值
 *  可以获取启动注册参数
 */
@Component
public class SimpleCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        //获取参数 String 列表形式
        //获取Spring程
        System.out.println("我CommandLineRunner被调用，获取参数数量："+args.length);
    }
}
