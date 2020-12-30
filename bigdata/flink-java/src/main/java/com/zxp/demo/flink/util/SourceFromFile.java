package com.zxp.demo.flink.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangxuepei
 * @since 3.0
 * 将文件中数模拟成为实时数据流
 */
public class SourceFromFile extends RichSourceFunction<String> {
    private volatile Boolean isRunning = true;

    @Override
    public void run(SourceContext ctx) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(EnvironmentUtil.startDir+"\\testData\\test.txt"));
        while (isRunning) {
            String line = bufferedReader.readLine();
           // System.out.println(line);
            if (StringUtils.isBlank(line)) {
                continue;
            }
            ctx.collect(line);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }
}
