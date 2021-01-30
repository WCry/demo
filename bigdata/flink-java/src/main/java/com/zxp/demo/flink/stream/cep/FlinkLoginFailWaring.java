package com.zxp.demo.flink.stream.cep;


import com.zxp.demo.flink.entry.LoginEvent;
import com.zxp.demo.flink.entry.LoginWarning;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zhangxuepei
 * @since 3.0
 * 采用Flink的CEP检测IP登录失败警告
 */
public class FlinkLoginFailWaring {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<LoginEvent> loginEventStream = env.fromCollection(Arrays.asList(
                new LoginEvent("1","192.168.0.1","fail"),
                new LoginEvent("1","192.168.0.2","fail"),
                new LoginEvent("1","192.168.0.3","fail"),
                new LoginEvent("2","192.168.10,10","success")

        ));
        //CEP 规则 匹配模式是 连续在三秒内连续失败的检测
        Pattern<LoginEvent, LoginEvent> loginFailPattern = Pattern.<LoginEvent>
                begin("begin")
                .where(new IterativeCondition<LoginEvent>() {
                    private static final long serialVersionUID = 2241014007503620137L;

                    @Override
                    public boolean filter(LoginEvent loginEvent, Context context) throws Exception {
                        return loginEvent.getType().equals("fail");
                    }
                })
                .next("next")
                .where(new IterativeCondition<LoginEvent>() {
                    private static final long serialVersionUID = 4928819689938249022L;

                    @Override
                    public boolean filter(LoginEvent loginEvent, Context context) throws Exception {
                        return loginEvent.getType().equals("fail");
                    }
                })
                .within(Time.seconds(3));
        PatternStream<LoginEvent> patternStream = CEP.pattern(loginEventStream.keyBy(LoginEvent::getUserId), loginFailPattern);
        DataStream<LoginWarning> loginFailDataStream = patternStream.select((Map<String, List<LoginEvent>> pattern) -> {
            List<LoginEvent> first = pattern.get("begin");
            List<LoginEvent> second = pattern.get("next");
            return new LoginWarning(second.get(0).getUserId(),second.get(0).getIp(), second.get(0).getType());
        });
        loginFailDataStream.print();
        env.execute();
    }

}
