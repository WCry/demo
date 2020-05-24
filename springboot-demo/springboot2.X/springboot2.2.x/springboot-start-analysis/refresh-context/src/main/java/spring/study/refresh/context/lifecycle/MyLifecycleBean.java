package spring.study.refresh.context.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * 定义 Springboot 生命周期，智能生命周期
 *   Springboot 生命周期从初始化完搜索Bean后开始，在应用程序关闭时候结束。
 *  智能生命周期 https://blog.csdn.net/catoop/article/details/71274561
 */
@Component
public class MyLifecycleBean implements SmartLifecycle {
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
        System.out.println("------ my lifecycle bean stop callback");
    }

    @Override
    public void start() {
        running = true;
        //生命周期开始 初始化完成之后执行的 比较晚
        System.out.println("------ my lifecycle bean start");
    }

    @Override
    public void stop() {
        //似乎 普通测试时候未执行
        running = false;
        System.out.println("------ my lifecycle bean stop");
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public int getPhase() {
        return 0;
    }
}
