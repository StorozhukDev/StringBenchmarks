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
public class ConcatStrings {

    @State(Scope.Benchmark)
    public static class Str {
        public static String FORMATTED_STRING = "Hello %s! You can %s strings %s! All you need is %s!";
        public static String NAME = "friend";
        public static String CREATE = "create";
        public static String SIMPLY = "simply";
        public static String JAVA = "Java";
    }

    @State(Scope.Benchmark)
    public static class FinalStr {
        public final static String FORMATTED_STRING = "Hello %s! You can %s strings %s! All you need is %s!";
        public final static String NAME = "friend";
        public final static String CREATE = "create";
        public final static String SIMPLY = "simply";
        public final static String JAVA = "Java";
    }

    @Benchmark
    public String stringFormat() {
        return String.format(Str.FORMATTED_STRING,
                Str.NAME, Str.CREATE, Str.SIMPLY, Str.JAVA);
    }

    @Benchmark
    public String stringAdd() {
        return "Hello " + Str.NAME
                + "! You can " + Str.CREATE
                + " strings " + Str.SIMPLY
                + "! All you need is " + Str.JAVA
                + "!";
    }

    @Benchmark
    public String stringConcat() {
        return "Hello ".concat(Str.NAME)
                .concat("! You can ").concat(Str.CREATE)
                .concat(" strings ").concat(Str.SIMPLY)
                .concat("! All you need is ").concat(Str.JAVA)
                .concat("!");
    }

    @Benchmark
    public String stringBuild() {
        return new StringBuilder().append("Hello ").append(Str.NAME)
                .append("! You can ").append(Str.CREATE)
                .append(" strings ").append(Str.SIMPLY)
                .append("! All you need is ").append(Str.JAVA)
                .append("!").toString();
    }

    @Benchmark
    public String stringBuff() {
        return new StringBuffer().append("Hello ").append(Str.NAME)
                .append("! You can ").append(Str.CREATE)
                .append(" strings ").append(Str.SIMPLY)
                .append("! All you need is ").append(Str.JAVA)
                .append("!").toString();
    }

    @Benchmark
    public String stringFormatFinal() {
        return String.format(FinalStr.FORMATTED_STRING,
                Str.NAME, FinalStr.CREATE, FinalStr.SIMPLY, FinalStr.JAVA);
    }

    @Benchmark
    public String stringAddFinal() {
        return "Hello " + FinalStr.NAME
                + "! You can " + FinalStr.CREATE
                + " strings " + FinalStr.SIMPLY
                + "! All you need is " + FinalStr.JAVA
                + "!";
    }

    @Benchmark
    public String stringConcatFinal() {
        return "Hello ".concat(FinalStr.NAME)
                .concat("! You can ").concat(FinalStr.CREATE)
                .concat(" strings ").concat(FinalStr.SIMPLY)
                .concat("! All you need is ").concat(FinalStr.JAVA)
                .concat("!");
    }

    @Benchmark
    public String stringBuildFinal() {
        return new StringBuilder().append("Hello ").append(FinalStr.NAME)
                .append("! You can ").append(FinalStr.CREATE)
                .append(" strings ").append(FinalStr.SIMPLY)
                .append("! All you need is ").append(FinalStr.JAVA)
                .append("!").toString();
    }

    @Benchmark
    public String stringBuffFinal() {
        return new StringBuffer().append("Hello ").append(FinalStr.NAME)
                .append("! You can ").append(FinalStr.CREATE)
                .append(" strings ").append(FinalStr.SIMPLY)
                .append("! All you need is ").append(FinalStr.JAVA)
                .append("!").toString();
    }

    @Benchmark
    public String stringConcatTwo() {
        return Str.CREATE.concat(Str.SIMPLY);
    }

    @Benchmark
    public String stringAddTwo() {
        return Str.CREATE + Str.SIMPLY;
    }

    @Benchmark
    public String stringConcatTwoFinal() {
        return FinalStr.CREATE.concat(FinalStr.SIMPLY);
    }

    @Benchmark
    public String stringAddTwoFinal() {
        return FinalStr.CREATE + FinalStr.SIMPLY;
    }
}
