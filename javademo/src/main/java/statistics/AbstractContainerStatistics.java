package statistics;

import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public abstract class AbstractContainerStatistics extends AbstractDisassembleStatistics{
    public AbstractContainerStatistics(){
        super();
    }
    public AbstractContainerStatistics(List<StatisticsStrategy> dependencyList){
        super(dependencyList);
    }
    public void statistics(Double currentNumber) {
    }
}
