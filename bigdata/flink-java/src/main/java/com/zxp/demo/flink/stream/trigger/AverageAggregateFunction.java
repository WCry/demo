package com.zxp.demo.flink.stream.trigger;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * @author zhangxuepei
 * @since 3.0
 */
public class AverageAggregateFunction implements AggregateFunction<Tuple2<String, Integer>, Tuple2<Integer, Integer>, Double> {
    /**
     * 聚合的初始化  创建累加器

     * @return
     */
    @Override
    public Tuple2<Integer, Integer> createAccumulator() {
        return new Tuple2<>(0, 0);
    }

    /**
     * 累加器和当前值
     *
     * @param value
     * @param accumulator
     *
     * @return
     */
    @Override
    public Tuple2<Integer, Integer> add(Tuple2<String, Integer> value, Tuple2<Integer, Integer> accumulator) {
        return new Tuple2<>(accumulator.f0 + value.f1, +accumulator.f1 + 1);
    }

    /**
     * 获取最终结果     获取平均值等
     *
     * @param accumulator
     *
     * @return
     */
    @Override
    public Double getResult(Tuple2<Integer, Integer> accumulator) {
        return ((double) accumulator.f0) / accumulator.f1;
    }

    /**
     * 主要原因合并时间窗口函数的结果
     * 合并前后两个对象
     *AggregateFunction中的merge方法仅SessionWindow会调用该方法，
     * 如果time window是不会调用的，merge方法即使返回null也是可以的。
     * 可以看看官方的文档中的描述和结合翻看源码就可以搞清楚了
     * 官网中的描述大概的意思是：因为会话窗口没有固定的起始时间和结束时间，
     * 他们被运算不同于滚动窗口和滑动窗口。
     * 本质上，会话窗口会为每一批相邻两条数据没有大于指定间隔时间的数据merge到以一起。
     * 为了数据能够被merge，会话窗口需要一个merge的触发器和一个可以merge的WindowFunction，
     * 比如ReduceFunction、AggregateFunction或者ProcessWindowFunction，需要注意的是FoldFunction不能merge！
     *
     * 作者：星哥
     * 链接：https://www.zhihu.com/question/346639699/answer/977856761
     * 来源：知乎
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param a 根据时间窗口函数计算的上一个结果
     * @param b 根据时间窗口函数计算的下一个结果
     *
     * @return
     */
    @Override
    public Tuple2<Integer, Integer> merge(Tuple2<Integer, Integer> a, Tuple2<Integer, Integer> b) {
        return new Tuple2<>(a.f0 + b.f0, +a.f1 + b.f1);
    }
}
