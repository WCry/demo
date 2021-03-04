package statistics;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class SumStatisticsStrategy implements StatisticsStrategy {
    private Double sum=new Double(0);
    public void statistics(Double currentNumber) {
        sum += currentNumber;
    }
    public Double getStatisticsResult() {
        return sum;
    }
    @Override
    public int getOrder() {
        return 1;
    }
}
