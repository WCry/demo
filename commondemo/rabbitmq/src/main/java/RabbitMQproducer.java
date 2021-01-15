import java.sql.Time;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class RabbitMQproducer {
    public static void main(String[] args) throws Exception {
        String[] ss = {"2020-04-20 10:12:44", "2020-04-20 8:50:44", "2020-04-20 14:12:44", "2020-04-20 18:12:44", "2020-04-20 20:12:44"};
        for (String date : ss) {
            cac(TimeUnit.DAYS.toDays(1L), date,TimeUnit.HOURS.toHours(16L));
        }
    }

    /**
     * @param w 窗口大小
     * @param d 事件时间
     * @param o 偏移时间
     * @throws Exception
     */
    public static void cac(long w, String d, long o) throws Exception {
        LocalDateTime parse = LocalDateTime.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss"));

        long timestamp = parse.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();

        long offset = o;
        long windowSize = w;

        long startTime = timestamp - (timestamp - offset + windowSize) % windowSize;
        System.out.println("offset:" + offset + ", windowSize: " + windowSize + ", " + startTime + "--" + LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()));
    }
}
