package statistics;

import java.util.Collection;

/**
 * @author zhangxuepei
 * @since 3.0
 * 可以拆解的统计策略
 */
public interface DisassembleStatistics extends StatisticsStrategy {
    //整个策略计算的过程中进行替换策略  替换完成可以减少运算次数
    void replaceStatisticsStrategy(StatisticsStrategy statisticsStrategy);
    //获取依赖的统计策略
    Collection<StatisticsStrategy> dependenceStatisticsStrategy();
}
