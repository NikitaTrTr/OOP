package ru.nsu.ntatarinov;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Benchmark for multithreading prime checker.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkForMultithreadingPrimeChecker {

    @Param({"1", "4", "16"})
    private int threads = 1;
    List<Integer> data = List.of(999999017, 999999029, 999999043,
        999999059, 999999067, 999999103, 999999107, 999999113, 999999131, 999999137, 999999151,
        999999163, 999999181, 999999191, 999999193, 999999197, 999999223, 999999229, 999999323);

    @Benchmark
    public void threadCheckerTest(Blackhole blackhole) throws InterruptedException {
        MultithreadingChecker checker = new MultithreadingChecker(data, threads);
        blackhole.consume(checker.check());
    }
}
