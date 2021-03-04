package statistics;

import java.util.Arrays;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class AverageStatistics extends AbstractContainerStatistics  {

    public AverageStatistics(SumStatisticsStrategy sumStatisticsStrategy,
                             CountStatisticsStrategy countStatisticsStrategy) {
        super(Arrays.asList(sumStatisticsStrategy, countStatisticsStrategy));
    }

    public AverageStatistics() {
        CountStatisticsStrategy countStatisticsStrategy = new CountStatisticsStrategy();
        SumStatisticsStrategy sumStatisticsStrategy = new SumStatisticsStrategy();
        this.initDependencyStrategy(Arrays.asList(sumStatisticsStrategy, countStatisticsStrategy));
    }

    public Double getStatisticsResult() {
        return getStatisticsStrategy(SumStatisticsStrategy.class).getStatisticsResult() /
                getStatisticsStrategy(CountStatisticsStrategy.class).getStatisticsResult();
    }
}
