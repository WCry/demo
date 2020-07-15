package Time;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * JAVA 8 API 中  时间计算
 * https://www.cnblogs.com/wbxk/p/9598518.html
 */
public class TimeOperationTest {
    public static void main(String[] args) throws InterruptedException {
        LocalDateTime localDateTime = LocalDateTime.now();
        //计算周期性质的时间差值
        long chronoUnit = ChronoUnit.HOURS.between(localDateTime, LocalDateTime.now());
        System.out.println("时间差（纳秒）：" + chronoUnit);
        Duration durationOff = Duration.ofHours(1);
        durationOff = durationOff.plusMinutes(50);
        //时间区间  计算时间差
        Duration duration = Duration.between(localDateTime, LocalDateTime.now());
        System.out.println(duration);
        //只会输出整数部分
        System.out.println(durationOff.toHours());
        System.out.println(durationOff);
        //推荐使用 Instant 中文意思 片刻 瞬间
        Instant instant = Instant.now();
        //获取当前 毫秒时间
        System.currentTimeMillis();
        //获取纳秒级别的时间
        System.nanoTime();
        // parseToDate方法作用是将String转为LocalDate，略。
        LocalDate date1 =LocalDate.of(2020,05,12);
        LocalDate date2 = LocalDate.of(2021,05,13);
        System.out.println(Period.between(date1, date2).getYears());
        // 计算日期间隔获取
        int period = Period.between(date1,date2).getDays();
        System.out.println(period+"");


    }
}
