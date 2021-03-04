package statistics;

/**
 * @author zhangxuepei
 * @since 3.0
 * 具有先后顺序的统计策略,依赖于前一个统计结果需要再次遍历元素得到当前的统计结果
 */
public interface AfterStatistics extends DisassembleStatistics {
    void preStrategyComplete();
}
