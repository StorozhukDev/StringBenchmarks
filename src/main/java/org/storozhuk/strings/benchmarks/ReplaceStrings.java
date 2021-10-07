package org.storozhuk.strings.benchmarks;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.*;

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
public class ReplaceStrings {
    public static String regularString = "Example string data here!";

    @Benchmark
    public String benchmarkStringReplace() {
        return regularString.replace("data", " D-A-T-A");
    }

    @Benchmark
    public String benchmarkStringUtilsReplace() {
        return StringUtils.replace(regularString, "data", " D-A-T-A");
    }
}
