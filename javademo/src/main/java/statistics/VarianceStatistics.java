package statistics;

import java.util.Arrays;

/**
 * @author zhangxuepei
 * @since 3.0
 * 需要先经过求平均数和数量统计然后求取方差
 */
public class VarianceStatistics extends AbstractFollowStatistics implements AfterStatistics {
    private Integer count;
    private Double mean;
    private Double squareSum=new Double(0);

    public VarianceStatistics(AverageStatistics averageStatisticsStrategy,
                              CountStatisticsStrategy countStatisticsStrategy) {
        super(Arrays.asList(averageStatisticsStrategy, countStatisticsStrategy));
    }
    public VarianceStatistics() {
        super();
        CountStatisticsStrategy countStatisticsStrategy=new CountStatisticsStrategy();
        AverageStatistics averageStatisticsStrategy =new AverageStatistics();
        this.initDependencyStrategy(Arrays.asList(countStatisticsStrategy, averageStatisticsStrategy));
        this.order++;
    }

    public void statistics(Double currentNumber) {
        squareSum += Math.pow((currentNumber - mean), 2);
    }

    public Double getStatisticsResult() {
        return squareSum / count;
    }

    @Override
    public void preStrategyComplete() {
        mean = getStatisticsStrategy(AverageStatistics.class).getStatisticsResult();
        count = getStatisticsStrategy(CountStatisticsStrategy.class).getStatisticsResult().intValue();
    }
}
