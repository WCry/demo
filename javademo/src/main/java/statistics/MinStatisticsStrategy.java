package statistics;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class MinStatisticsStrategy implements StatisticsStrategy {
    private Double min = Double.MAX_VALUE;

    public void statistics(Double currentNumber) {
        min = Math.min(currentNumber, min);
    }

    public Double getStatisticsResult() {
        return min;
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
