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
public class ReverseStrings {
    @State(Scope.Thread)
    public static class StateVariables {
        public String s1 = "abc def ghi jkl mno pqr stu vwx yz";
    }

    @Benchmark
    public String reverseChar(StateVariables variables) {
        char[] array = variables.s1.toCharArray();
        int charCnt = array.length;
        char[] reversed = new char[charCnt];
        for (int i = charCnt - 1; i >= 0; i--) {
            reversed[charCnt-i-1] = array[i];
        }
        System.out.println(new String(reversed));
        return new String(reversed);
    }

    @Benchmark
    public String reverseSb(StateVariables variables) {
        System.out.println(new StringBuilder(variables.s1).reverse());
        return new StringBuilder(variables.s1).reverse().toString();
    }
}
