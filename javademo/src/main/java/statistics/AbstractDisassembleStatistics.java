package statistics;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public abstract class AbstractDisassembleStatistics implements DisassembleStatistics {
    protected Integer order = 0;
    private HashMap<Class, StatisticsStrategy> hashMap;

    public AbstractDisassembleStatistics(List<StatisticsStrategy> dependencyList) {
        initDependencyStrategy(dependencyList);
    }
    public AbstractDisassembleStatistics(){

    }
    protected void initDependencyStrategy(List<StatisticsStrategy> dependencyList){
        this.hashMap = new HashMap();
        for (StatisticsStrategy statisticsStrategy : dependencyList) {
            if (statisticsStrategy.getOrder() > order) {
                this.order = statisticsStrategy.getOrder();
            }
            this.hashMap.put(statisticsStrategy.getClass(), statisticsStrategy);
        }
    }

    @Override
    public int getOrder() {
        return order;
    }

    public <T> T  getStatisticsStrategy(Class<T> className){
        return (T)hashMap.get(className);
    }

    @Override
    public void replaceStatisticsStrategy(StatisticsStrategy statisticsStrategy) {
        this.hashMap.replace(statisticsStrategy.getClass(), statisticsStrategy);
        internalReplace(statisticsStrategy);
    }
    public  void internalReplace(StatisticsStrategy statisticsStrategy){};

    @Override
    public Collection<StatisticsStrategy> dependenceStatisticsStrategy() {
        return Collections.unmodifiableCollection(this.hashMap.values());
    }
}
