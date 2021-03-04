package statistics;

import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public abstract class AbstractFollowStatistics extends AbstractDisassembleStatistics {
    public AbstractFollowStatistics(){
        super();
    }
    public AbstractFollowStatistics(List<StatisticsStrategy> dependencyList) {
        super(dependencyList);
        this.order+=1;
    }
}
