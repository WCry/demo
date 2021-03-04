package statistics;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class MaxStatisticsStrategy implements StatisticsStrategy {
    private Double max=Double.MIN_VALUE;

    public void statistics(Double currentNumber) {
        max = Math.max(max, currentNumber);
    }

    public Double getStatisticsResult() {
        return max;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
