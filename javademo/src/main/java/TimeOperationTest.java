import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
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
    }

    public void TestMyMaxHeap(int[] numbers, int k) {
        int[] heapMax = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (i == 0) {
                heapMax[0] = numbers[0];
            } else {
                int cu = numbers[i];
                int currIndex=0;
                for (int j = 0; j < i; j++) {
                    int heapleft = j << 1 + 1;
                    if(cu>heapMax[currIndex]){
                        int temp=cu;
                        cu =heapMax[currIndex];
                        heapMax[currIndex]=temp;
                    }
                    if(heapleft>((i-1)>>1)){
                        heapMax[heapleft]=cu;
                    }else{
                        int heapright = (j + 1) << 1;
                        if(heapright>(i<<1)){
                            //如果超出 直接进行赋值
                            heapMax[heapright]=cu;
                        }else{
                            if (heapright<i*2){
                                currIndex=heapleft;
                                if(heapMax[heapleft]>heapMax[heapright]){
                                    currIndex=heapright;
                                }
                            }
                            if(heapMax[currIndex]>cu){
                            }else{
                            }
                        }
                    }
                }
            }
        }
    }
}
