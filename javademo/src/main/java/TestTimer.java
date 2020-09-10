import com.sun.org.apache.xerces.internal.impl.xs.traversers.XSAttributeChecker;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestTimer {
    public static void main(String[] args) {
        Timer timer=new Timer();
        //调度最小时间是1毫秒
        //最小的线程睡眠时间是1毫秒
        // Thread.sleep(1);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("dssssssssssssssss");
            }
        }, 0, 1);
    }
}
