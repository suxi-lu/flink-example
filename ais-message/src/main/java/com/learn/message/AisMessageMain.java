package com.learn.message;

import org.apache.flink.api.java.utils.MultipleParameterTool;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class AisMessageMain {

    public static void main(String[] args) throws Exception {
        // Checking input parameters
        final MultipleParameterTool params = MultipleParameterTool.fromArgs(args);

        // set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // 1. stream

        // 2. transform

        // 3. sink

        // execute program
        env.execute("Streaming WordCount");
    }

}
