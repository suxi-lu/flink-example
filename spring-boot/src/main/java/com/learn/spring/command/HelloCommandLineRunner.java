package com.learn.spring.command;

import com.learn.spring.utils.AisParserUtils;
import com.learn.spring.vo.VisMessageVO;
import dk.dma.ais.message.AisMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.java.utils.MultipleParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
//@Component
public class HelloCommandLineRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("hello command run.");

        // Checking input parameters
        final MultipleParameterTool params = MultipleParameterTool.fromArgs(args);

        // set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

//        String input = "D:\\workspace\\github-learn\\flink-example\\ais-message\\src\\test\\resources\\stream_example.txt";
//        // 1. stream
//        DataStream<String> textStream = env.readTextFile(input);
//
//        DataStream<AisMessage> aisMessageStream = textStream
//                .map(AisParserUtils::parse)
//                .filter(Objects::nonNull);
//
//        aisMessageStream
//                .map(item -> {
//                    VisMessageVO vo = new VisMessageVO();
//                    vo.setUserId(item.getUserId());
//                    vo.setNum(1);
//                    return vo;
//                })
//                .keyBy("userId")
////                .timeWindow(Time.minutes(1))
////                .timeWindow(Time.minutes(1), Time.milliseconds(100))
//                .countWindow(100, 10)
//                .sum("num")
//
//                .print();

//        textStream.print();

        // 2. transform

        // 3. sink

        // execute program
//        env.execute("Streaming WordCount");

        log.info("hello command run end.");
    }

}
