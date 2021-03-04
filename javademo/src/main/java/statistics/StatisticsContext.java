package statistics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.ToDoubleFunction;

/**
 * @author zhangxuepei
 * @since 3.0
 * 统计策略的上下文
 */
public class StatisticsContext {
    private TreeMap<Integer,Collection<StatisticsStrategy>> calculateMultiMap;
    private Set<StatisticsStrategy> statisticsStrategiesList;
    private Integer maxRound = 1;

    public StatisticsContext(Collection<StatisticsStrategy> statisticsStrategies) {
        this.calculateMultiMap = new TreeMap();
        TreeMap sortedMap = new TreeMap();
        HashMap<Class, StatisticsStrategy> duplication = new HashMap();
        //提前将第一轮需要运算提取出来
        for (StatisticsStrategy statisticsStrategy : statisticsStrategies) {
            sortedMap.put(statisticsStrategy.getOrder(), statisticsStrategy);
            if(!(statisticsStrategy instanceof DisassembleStatistics)){
                duplication.put(statisticsStrategy.getClass(),statisticsStrategy);
                calculateMultiMap.putIfAbsent(statisticsStrategy.getOrder(),
                        new ArrayList<StatisticsStrategy>(){{add(statisticsStrategy);}});
                calculateMultiMap.computeIfPresent(statisticsStrategy.getOrder(),
                        (integer, statisticsStrategies1) -> {
                    statisticsStrategies1.add(statisticsStrategy);
                    return statisticsStrategies1;
                });
            }
        }
        Collection<Collection<StatisticsStrategy>> sortedStatisticsCollection = sortedMap.values();
        for (Collection<StatisticsStrategy> sortedStatistics : sortedStatisticsCollection) {
            for (StatisticsStrategy sortedStatistic : sortedStatistics) {
                recursionStatistics(duplication, calculateMultiMap, sortedStatistic);
            }
        }
    }

    public void completeStatisticsRound(Integer currentStatisticsRound) {
        Collection<StatisticsStrategy> abstractAfterDisassembleStatistics =
                calculateMultiMap.get(currentStatisticsRound+1);
        for (StatisticsStrategy disassembleStatistics : abstractAfterDisassembleStatistics) {
            if(disassembleStatistics instanceof AfterStatistics){
                AfterStatistics afterStatisticsStrategy=(AfterStatistics) disassembleStatistics;
                afterStatisticsStrategy.preStrategyComplete();
            }
        }
    }

    public Integer getMaxStatisticsRound() {
        return maxRound;
    }

    public void StatisticsContext(Set<StatisticsStrategy> statisticsStrategies, TreeMap calculateMultiMap) {
        this.calculateMultiMap = calculateMultiMap;
        this.statisticsStrategiesList = statisticsStrategies;
        Set<Integer> orders = calculateMultiMap.keySet();
        for (Integer order : orders) {
            if (order > maxRound) {
                this.maxRound = order;
            }
        }
    }

    public void StatisticsContext(Set<StatisticsStrategy> statisticsStrategies, TreeMap calculateMultiMap,
                                  Integer maxRound) {
        this.calculateMultiMap = calculateMultiMap;
        this.statisticsStrategiesList = statisticsStrategies;
        this.maxRound = maxRound;
    }

    public void StatisticsContext(Set<StatisticsStrategy> statisticsStrategies,
                                  Set<StatisticsStrategy> calculateStatistics) {
        this.statisticsStrategiesList = statisticsStrategies;
        for (StatisticsStrategy calculateStatistic : calculateStatistics) {
            Integer order = calculateStatistic.getOrder();
            calculateMultiMap.putIfAbsent(calculateStatistic.getOrder(),
                    new ArrayList<StatisticsStrategy>(){{add(calculateStatistic);}});
            calculateMultiMap.computeIfPresent(calculateStatistic.getOrder(),
                    (integer, statisticsStrategies1) -> {
                        statisticsStrategies1.add(calculateStatistic);
                        return statisticsStrategies1;
                    });
            if (order > maxRound) {
                maxRound = order;
            }
        }
    }

    public Set<StatisticsStrategy> getStatisticsStrategiesList() {
        return statisticsStrategiesList;
    }

    public void statistics(Integer currentRounds, Double statistics) {
        Collection<StatisticsStrategy> statisticsStrategies = calculateMultiMap.get(currentRounds);
        for (StatisticsStrategy statisticsStrategy : statisticsStrategies) {
            statisticsStrategy.statistics(statistics);
        }
    }

    public void statistics(List<Double> currentRounds) {
        for (Integer i = 1; i <= maxRound; i++) {
            for (Double needStatistic : currentRounds) {
                statistics(i, needStatistic);
            }
            completeStatisticsRound(i);
        }
    }

    public <T> void statistics(List<T> currentRounds, ToDoubleFunction<T> consumer) {
        for (Integer i = 1; i <= maxRound; i++) {
            for (T needStatistic : currentRounds) {
                statistics(i, consumer.applyAsDouble(needStatistic));
            }
            completeStatisticsRound(i);
        }
    }

    //递归进行解析 可能存在重复的统计解析
    private void recursionStatistics(HashMap<Class, StatisticsStrategy> duplication, TreeMap calculateMultiMap,
                                     StatisticsStrategy statisticsStrategy) {
        if (!(statisticsStrategy instanceof DisassembleStatistics)) {
            //直接进行运算的统计
            if (!duplication.containsKey(statisticsStrategy.getClass())) {
                duplication.put(statisticsStrategy.getClass(), statisticsStrategy);
                calculateMultiMap.put(statisticsStrategy.getOrder(), statisticsStrategy);
                if (statisticsStrategy.getOrder() > maxRound) {
                    maxRound = statisticsStrategy.getOrder();
                }
            }
            return;
        }
        //可以 进行分解统计
        DisassembleStatistics disassembleStatistics =
                (DisassembleStatistics) statisticsStrategy;
        Collection<StatisticsStrategy> statisticsStrategies =
                disassembleStatistics.dependenceStatisticsStrategy();
        if (disassembleStatistics instanceof AfterStatistics) {
            duplication.put(statisticsStrategy.getClass(), statisticsStrategy);
            calculateMultiMap.put(statisticsStrategy.getOrder(), statisticsStrategy);
            if (statisticsStrategy.getOrder() > maxRound) {
                maxRound = statisticsStrategy.getOrder();
            }
        }
        for (StatisticsStrategy strategy : statisticsStrategies) {
            StatisticsStrategy repeatStatistics = duplication.get(strategy.getClass());
            if (!Objects.isNull(repeatStatistics)) {
                disassembleStatistics.replaceStatisticsStrategy(repeatStatistics);
                continue;
            }
            recursionStatistics(duplication, calculateMultiMap, strategy);
        }
        return;
    }
}
