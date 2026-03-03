package dev.expert.perf;

import dev.expert.concurrency.SynchronizedRateLimiter;
import dev.expert.concurrency.StampedLockRateLimiter;
import dev.expert.concurrency.AtomicSchedulerRateLimiter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class LockBenchmark {
    private SynchronizedRateLimiter sync;
    private StampedLockRateLimiter stamped;
    private AtomicSchedulerRateLimiter atomic;

    @Setup
    public void setUp() {
        sync = new SynchronizedRateLimiter(1000);
        stamped = new StampedLockRateLimiter(1000);
        atomic = new AtomicSchedulerRateLimiter(1000);
    }

    @Benchmark
    public boolean syncLimiter() {
        return sync.tryAcquire();
    }

    @Benchmark
    public boolean stampedLimiter() {
        return stamped.tryAcquire();
    }

    @Benchmark
    public boolean atomicLimiter() {
        return atomic.tryAcquire();
    }
}

























































/*
ANSWER KEY:
// No additional logic; JMH benchmark already complete.
*/
