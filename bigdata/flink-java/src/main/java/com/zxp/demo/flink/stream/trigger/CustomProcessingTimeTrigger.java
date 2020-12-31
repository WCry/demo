package com.zxp.demo.flink.stream.trigger;

import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.GlobalWindow;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

/**
 * @author zhangxuepei
 * @since 3.0
 * 自定义触发时间
 */
public class CustomProcessingTimeTrigger extends Trigger<Object, GlobalWindow> {
    private static final long serialVersionUID = 1L;
    private static int flag = 0;

    private CustomProcessingTimeTrigger() {
    }

    /**
     * 创建一个自定义触发器对象
     */
    public static CustomProcessingTimeTrigger create() {
        return new CustomProcessingTimeTrigger();
    }

    /**
     * 每一个元素到达都会调用
     * 简单  当元素个数超过9个然后开始运行   类似于CountWindows
     * @param element
     * @param timestamp
     * @param window
     * @param ctx
     * @return
     */
    @Override
    public TriggerResult onElement(Object element, long timestamp, GlobalWindow window, TriggerContext ctx) {
        ctx.registerProcessingTimeTimer(window.maxTimestamp());
        // CONTINUE是代表不做输出，也即是，此时我们想要实现比如100条输出一次，
        // 而不是窗口结束再输出就可以在这里实现。
        if (flag > 9) {
            System.out.println("触发计算-> flag: " + flag);
            flag = 0;
            return TriggerResult.FIRE;
        } else {
            flag++;
            return TriggerResult.CONTINUE;
        }
    }

    /**
     *   事件时间更新的时候触发
     * @param time
     * @param window
     * @param ctx
     * @return
     * @throws Exception
     */
    @Override
    public TriggerResult onEventTime(long time, GlobalWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.FIRE_AND_PURGE;
    }

    /**
     * 处理时间的时候
     * @param time
     * @param window
     * @param ctx
     * @return
     */
    @Override
    public TriggerResult onProcessingTime(long time, GlobalWindow window, TriggerContext ctx) {
        return TriggerResult.FIRE_AND_PURGE;
    }

    @Override
    public void clear(GlobalWindow window, TriggerContext ctx) throws Exception {
        //清除时间
        ctx.deleteProcessingTimeTimer(window.maxTimestamp());
    }

    @Override
    public boolean canMerge() {
        return true;
    }

    @Override
    public void onMerge(GlobalWindow window, OnMergeContext ctx) {
        // only register a timer if the time is not yet past the end of the merged window
        // this is in line with the logic in onElement(). If the time is past the end of
        // the window onElement() will fire and setting a timer here would fire the window twice.
        long windowMaxTimestamp = window.maxTimestamp();
        if (windowMaxTimestamp > ctx.getCurrentProcessingTime()) {
            ctx.registerProcessingTimeTimer(windowMaxTimestamp);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "ProcessingTimeTrigger()";
    }
}


