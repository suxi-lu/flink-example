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
@Component
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

        String input = "D:\\workspace\\github-learn\\flink-example\\ais-message\\src\\test\\resources\\stream_example.txt";
        // 1. stream
//        DataStream<String> textStream = env.readTextFile(input);
        DataStream<String> textStream = env.fromElements(
                "$PGHP,1,2010,6,11,11,46,11,390,231,45,992190934,1,2B*5B",
                "!AIVDM,1,1,,B,402=481uaUcf;OQ55JS9ITi025Jp,0*2B",
                "$PGHP,1,2010,6,11,11,46,12,386,219,24,992190924,1,12*20",
                "!AIVDM,1,1,,B,?3oL?R0p<6I0D00,2*12",
                "$PGHP,1,2010,6,11,11,46,12,451,219,1,992190917,1,58*14",
                "!AIVDM,1,1,,A,33nr7t001f13KNTOahh2@QpF00vh,0*58",
                "$PGHP,1,2010,6,11,11,46,12,470,219,,2190064,1,5D*57"
        );

        DataStream<AisMessage> aisMessageStream = textStream
                .map(AisParserUtils::parse)
                .filter(Objects::nonNull);

        aisMessageStream
                .map(item -> {
                    VisMessageVO vo = new VisMessageVO();
                    vo.setUserId(item.getUserId());
                    vo.setNum(1);
                    return vo;
                })
                .keyBy("userId")
//                .timeWindow(Time.minutes(1))
//                .timeWindow(Time.minutes(1), Time.milliseconds(100))
                .countWindow(100, 10)
                .sum("num")

                .print();

        textStream.print();

        // 2. transform

        // 3. sink

        // execute program
        env.execute("Streaming WordCount");

        log.info("hello command run end.");
    }

}
