package org.storozhuk.strings.benchmarks;

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
public class IntToStrings {
    public static Integer number = 1234567;

    @Benchmark
    public String stringValueOf() {
        return String.valueOf(number);
    }

    @Benchmark
    public String integerToString() {
        return Integer.toString(number);
    }
}
