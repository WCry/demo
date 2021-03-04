package statistics;

import java.util.Arrays;

/**
 * @author zhangxuepei
 * @since 3.0
 * 标准差计算
 */
public class StanderVarianceStatistics extends AbstractContainerStatistics {
    public StanderVarianceStatistics(VarianceStatistics lmsStatisticsStrategy) {
        super(Arrays.asList(lmsStatisticsStrategy));
    }

    public StanderVarianceStatistics(){
        super();
        VarianceStatistics varianceStatistics=new VarianceStatistics();
        this.initDependencyStrategy(Arrays.asList(varianceStatistics));
    }

    public Double getStatisticsResult() {
        return Math.sqrt(getStatisticsStrategy(VarianceStatistics.class).getStatisticsResult());
    }
}
