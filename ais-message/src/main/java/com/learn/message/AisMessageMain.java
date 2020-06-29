package com.learn.message;

import com.learn.message.utils.AisParserUtils;
import dk.dma.ais.message.AisMessage;
import dk.dma.ais.sentence.Vdm;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.java.utils.MultipleParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Objects;

public class AisMessageMain {

    public static void main(String[] args) throws Exception {
        // Checking input parameters
        final MultipleParameterTool params = MultipleParameterTool.fromArgs(args);

        // set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        String input = "D:\\workspace\\git\\flink-example\\ais-message\\src\\test\\resources\\stream_example.txt";
        // 1. stream
        DataStream<String> textStream = env.readTextFile(input);

        DataStream<AisMessage> aisMessageStream = textStream
                .map(AisParserUtils::parse)
                .filter(Objects::nonNull);

        aisMessageStream
                .keyBy(AisMessage::getUserId)
                .print();


//        textStream.print();

        // 2. transform

        // 3. sink

        // execute program
        env.execute("Streaming WordCount");
    }

}
