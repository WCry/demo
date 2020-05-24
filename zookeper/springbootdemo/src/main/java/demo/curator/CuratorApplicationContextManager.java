package demo.curator;

import demo.zookeepernative.FairSelectLeader;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@RestController
@RequestMapping(value = "/curator")
public class CuratorApplicationContextManager implements ApplicationContextAware {
   private ApplicationContext applicationContext;
    private Timer timer;
    @Autowired
   private CuratorSelectLeader selectLeader;
    @GetMapping(value = "/close")
    public String closeLeader() {
        selectLeader.closeSelect();
        //关闭之后 不能进行返回  可以设置一个延迟执行
        timer=new Timer();
//        timer.schedule(new TimerTask(){
//            @Override
//            public void run() {
//                //优雅关闭 SpringBoot 应用
//                ConfigurableApplicationContext configurableApplicationContext=
//                        (ConfigurableApplicationContext)applicationContext;
//                configurableApplicationContext.close();
//            }
//        },1000);
        return "关闭节点了！";
    }
    @GetMapping(value = "/status")
    public String getStatus() {
        if(selectLeader.getLeader()){
            return "我是selectLeader！";
        }else{
            return "我是follower！";
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
