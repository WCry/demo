import com.google.common.collect.Maps;
import org.graalvm.util.CollectionsUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class TestTimerSchedule {
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
