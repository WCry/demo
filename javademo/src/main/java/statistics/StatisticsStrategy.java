package statistics;

/**
 * @author zhangxuepei
 * @since 3.0
 * 统计策略
 */
public interface StatisticsStrategy {
    /**
     * 当前需要统计值
     * @param number
     */
    void statistics(Double number);

    /**
     * 获取统计结果
     * @return
     */
    Double getStatisticsResult();

    /**
     * 统计策略的顺序
     * @return
     */
    int getOrder();
}
