package spring.study.startup;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * 定义 Springboot 生命周期，智能生命周期
 *   Springboot 生命周期从初始化完搜索Bean后开始，在应用程序关闭时候结束。
 *  智能生命周期 https://blog.csdn.net/catoop/article/details/71274561
 */
@Component
public class MyLifecycleBean implements SmartLifecycle , ApplicationContextAware {
    /**
     * 记录是否在运行
     */
    private boolean running = false;

    /**
     * 表明是否自动开始
      * @return
     */
    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        //Springboot 停止时候执行
        //所有停止完成后执行
        System.out.println("SmartLifecycle  智能化生命周期停止，Runnable");
    }

    @Override
    public void start() {
        //刷新Context时候进行初始化Bean，初始化Bean之后开启智能化声明周期
        running = true;
        System.out.println("SmartLifecycle  智能化生命周期开始，表明所有的bean都初始化完成");
    }

    @Override
    public void stop() {
        //似乎 普通测试时候未执行
        running = false;
        System.out.println("SmartLifecycle  智能化生命周期停止");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //初始化所有Bean之后开始 上下文感知  在上下文发生改变之后
        System.out.println(applicationContext);
    }
}
