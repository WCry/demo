package Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 每天面临着大量的服务调用。为了便于服务统计和管控，需要统计每个服务单位时间内的调用次数
 */
public class StatisticService {
    public Map<String, List<Long>> serviceData = new HashMap<>();

    public void invokeService(String serviceName) {
        serviceData.computeIfPresent(serviceName, (key, value) -> {
            value.add(System.currentTimeMillis());
            return value;
        });
        serviceData.putIfAbsent(serviceName, new ArrayList<Long>() {{
            add(System.currentTimeMillis());
        }});
    }

    public long statistics(String serviceName, Long startTime, Long endTime) {
        List<Long> serviceTimes = serviceData.get(serviceName);
        return serviceTimes.parallelStream().filter(serviceTime -> {
            if (serviceTime < endTime && serviceTime > startTime) {
                return true;
            } else {
                return false;
            }
        }).count();
    }
}
