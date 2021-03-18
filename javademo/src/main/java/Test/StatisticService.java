package Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

/**
 * 每天面临着大量的服务调用。为了便于服务统计和管控，需要统计每个服务单位时间内的调用次数
 */
public class StatisticService {

    //时间窗口  可能因为GC回收导致
    public static ConcurrentHashMap<String, LongAdder> serviceData = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
        //利用定时任务执行窗口的滑动
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            synchronized (serviceData) {
                System.out.println("统计结果:" + statistics("dd/ddd/dd"));
                serviceData.clear();
            }
        }, 0, 1, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
                System.out.println("其他的调度");
        }, 0, 1, TimeUnit.SECONDS);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000000; i++) {
            threadPoolExecutor.execute(() -> {
                invokeService("dd/ddd/dd");
            });
        }
        threadPoolExecutor.shutdown();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void invokeService(String serviceName) {
        synchronized (serviceData) {
            LongAdder value = serviceData.get(serviceName);
            if (null != serviceData.get(serviceName)) {
                //通过LongAdder 更加的提高线程 并行执行的效率
                value.increment();
            }
            serviceData.computeIfAbsent(serviceName, s -> {
                //过程是线程安全的 通过ConcurrentHashMap的线程安全进行设置Key
                LongAdder longAdder = new LongAdder();
                longAdder.increment();
                return longAdder;
            });
        }

    }

    public static long statistics(String serviceName) {
        return serviceData.getOrDefault(serviceName, new LongAdder()).longValue();
    }
}
