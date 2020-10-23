package Time;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * JAVA 8 API 中  时间计算
 * SimpleDateFormat 不是线程安全的类，存在全局变量。可以使用ThreadLocal进行操作
 * https://www.cnblogs.com/wbxk/p/9598518.html
 */
public class TimeOperationTest {
    public static void main(String[] args) throws InterruptedException {
        printWeekNumber();
    }
    private static void BasicUse(){
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
    private static void printMouthNumber(){
        Calendar calendar = Calendar.getInstance();
        //月份静态变量枚举从0开始，所以输出数字需要+1处理一下
        System.out.println("当前"+calendar.get(Calendar.MONTH)+"月份");
    }

    private static void formatDateYyDdHh(){
        //关于YYYY和yyyy的区别，（ISO-8601中规定）YYYY表示当前周所在的年，yyyy表示当前天所在年
        //如果出现跨年 YYYY将会表示错误
        //HH：24小时制度，hh：12小时制度
        //DD：当前年中第几天，dd:当前月中第几天
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);
        calendar.setMinimalDaysInFirstWeek(4);
        Date testDate = calendar.getTime();
        SimpleDateFormat dtf = new SimpleDateFormat("YYYY-MM-dd");
        System.out.println("2019-12-31 转 YYYY-MM-dd 格式后 " + dtf.format(testDate));
        int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println(weekNo);
    }
    private static void printWeekNumber(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, Calendar.JANUARY, 1);
        //会影响当前周 是当前年的第几周，因为周存在跨年的情况
        //如果当前周中有一天是，新的一年。将展示是新的一年的第一周
        //如果小于设置值，将展示是当前年的52周
        calendar.setMinimalDaysInFirstWeek(1);
        int weekNo = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println(weekNo);
    }


    private static void changeHourTime(){
        Calendar c = Calendar.getInstance();
        //按照12小时制 设置时间， 通过获取当前属于PM或者AM
        c.set(Calendar.HOUR, 10);
        //打印出的结果是24制
        System.out.println(c.getTime());
        //设置24小时制下  修改时间
        c.set(Calendar.HOUR_OF_DAY, 10);
        //打印出当前时间
        System.out.println(c.getTime());
    }
}
