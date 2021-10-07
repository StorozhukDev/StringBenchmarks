package org.storozhuk.strings.benchmarks;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author Oleksandr Storozhuk
 * @version 0.0.1
 * created on 05/10/21
 */
@BenchmarkMode({Mode.AverageTime})
@Warmup(iterations = 25, timeUnit = TimeUnit.MILLISECONDS, time = 100)
@Measurement(iterations = 100, timeUnit = TimeUnit.MILLISECONDS, time = 100)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgsAppend = {"-XX:+PrintGCDetails"})
public class ConcatDifferentStrings {

    @State(Scope.Benchmark)
    public static class StringContainer {
        @Param({"2", "5", "15"})
        public int stringCount;

        @Param({"2", "5", "15"})
        public int stringLen;

        private String[] data;
        private String[] workingData;

        // generate random string
        @Setup(Level.Trial)
        public void initData() {
            data = new String[stringCount];

            for (int i = 0; i < stringCount; i++) {
                int leftLimit = 65;
                int rightLimit = 122;
                Random random = new Random();

                data[i] = random.ints(leftLimit, rightLimit + 1)
                        .limit(stringLen)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
            }
        }

        @Setup(Level.Invocation)
        public void makeArrayCopy() {
            workingData = data.clone();
        }

        public String[] getData() {
            return workingData;
        }
    }

    @Benchmark
    public void stringAdd(StringContainer strings, Blackhole bh) {
        String string = "";
        for (int i = 0; i < strings.getData().length; i++) {
            string = string + strings.getData()[i];
        }
        bh.consume(string);
    }

    @Benchmark
    public void stringConcat(StringContainer strings, Blackhole bh) {
        String string = "";
        for (int i = 0; i < strings.getData().length; i++) {
            string = string.concat(strings.getData()[i]);
        }
        bh.consume(string);
    }

    @Benchmark
    public void stringBuild(StringContainer strings, Blackhole bh) {
        StringBuilder sb = new StringBuilder();
        String string;
        for (int i = 0; i < strings.getData().length; i++) {
            sb.append(strings.getData()[i]);
        }
        string = sb.toString();
        bh.consume(string);
    }

    @Benchmark
    public void stringBuff(StringContainer strings, Blackhole bh) {
        StringBuffer sb = new StringBuffer();
        String string;
        for (int i = 0; i < strings.getData().length; i++) {
            sb.append(strings.getData()[i]);
        }
        string = sb.toString();
        bh.consume(string);
    }
}
