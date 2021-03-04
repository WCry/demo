package statistics;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class CountStatisticsStrategy implements StatisticsStrategy {
    private Double count=0D;

    public void statistics(Double currentNumber) {
        count++;
    }

    public Double getStatisticsResult() {
        return count;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
