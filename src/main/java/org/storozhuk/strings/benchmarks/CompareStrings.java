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
public class CompareStrings {
    @State(Scope.Benchmark)
    public static class StateVariables {
        public static String s1 = "abc";
        public static String s2 = "def";
        public static String s3 = "ghi";
        public static String s4 = "ghi";
    }

    @Benchmark
    public boolean notEquals() {
        return StateVariables.s1.equals(StateVariables.s2);
    }

    @Benchmark
    public boolean notEqualsIgnoreCase() {
        return StateVariables.s1.equalsIgnoreCase(StateVariables.s2);
    }

    @Benchmark
    public boolean notCompareTo() {
        return StateVariables.s1.compareTo(StateVariables.s2) == 0;
    }

    @Benchmark
    public boolean notCompareToIgnoreCase() {
        return StateVariables.s1.compareToIgnoreCase(StateVariables.s2) == 0;
    }

    @Benchmark
    public boolean equals() {
        return StateVariables.s3.equals(StateVariables.s4);
    }

    @Benchmark
    public boolean equalsIgnoreCase() {
        return StateVariables.s3.equalsIgnoreCase(StateVariables.s4);
    }

    @Benchmark
    public boolean compareTo() {
        return StateVariables.s3.compareTo(StateVariables.s4) == 0;
    }

    @Benchmark
    public boolean compareToIgnoreCase() {
        return StateVariables.s3.compareToIgnoreCase(StateVariables.s4) == 0;
    }
}
