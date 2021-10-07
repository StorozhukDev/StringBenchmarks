package org.storozhuk.strings.benchmarks;

import org.openjdk.jmh.annotations.*;

import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

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
public class SplitStrings {
    public static String string = "abc def ghi jkl mno pqr stu vwx yz";

    @Benchmark
    public String stringTokenizerGetFirstToken() {
        StringTokenizer st = new StringTokenizer(string, " ");
        return st.nextToken();
    }

    @Benchmark
    public String stringSplitGetFirstToken() {
        String[] s = string.split(" ");
        return s[0];
    }

    @Benchmark
    public String stringTokenizerGetLastToken() {
        String s = null;
        StringTokenizer st = new StringTokenizer(string, " ");
        while(st.hasMoreTokens())
            s = st.nextToken();
        return s;
    }

    @Benchmark
    public String stringSplitGetLastToken() {
        String[] s = string.split(" ");
        return s[s.length-1];
    }

    @Benchmark
    public String stringPatternSplitGetLastToken() {
        Pattern pattern = Pattern.compile(" ");
        String[] s = pattern.split(string);
        return s[s.length-1];
    }
}
