package dev.expert.concurrency;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RateLimiterTest {

    private RateLimiter limiterUnderTest;

    static Stream<RateLimiter> limiters() {
        return Stream.of(
            new SynchronizedRateLimiter(2),
            new StampedLockRateLimiter(2),
            new AtomicSchedulerRateLimiter(2)
        );
    }

    @AfterEach
    void cleanup() {
        if (limiterUnderTest != null) {
            limiterUnderTest.close();
        }
    }

    @ParameterizedTest
    @MethodSource("limiters")
    void allowsOnlyConfiguredPermitsPerSecond(RateLimiter limiter) throws Exception {
        this.limiterUnderTest = limiter;

        boolean first = limiter.tryAcquire();
        boolean second = limiter.tryAcquire();
        boolean third = limiter.tryAcquire();

        assertThat(first).isTrue();
        assertThat(second).isTrue();
        assertThat(third).isFalse();

        TimeUnit.SECONDS.sleep(1);
        assertThat(limiter.tryAcquire()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("limiters")
    void isThreadSafe(RateLimiter limiter) throws Exception {
        this.limiterUnderTest = limiter;
        var pool = Executors.newFixedThreadPool(8);
        try {
            var futures = pool.invokeAll(List.<java.util.concurrent.Callable<Boolean>>of(
                () -> limiter.tryAcquire(),
                () -> limiter.tryAcquire(),
                () -> limiter.tryAcquire(),
                () -> limiter.tryAcquire(),
                () -> limiter.tryAcquire()
            ));
            long granted = futures.stream().filter(f -> {
                try { return Boolean.TRUE.equals(f.get()); } catch (Exception e) { throw new RuntimeException(e); }
            }).count();
            assertThat(granted).isLessThanOrEqualTo(2);
        } finally {
            pool.shutdownNow();
        }
    }

    @Test
    void racingTasksReturnsFastestResult() {
        var slow = (SupplierWithDelay<String>) () -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "interrupted";
            }
            return "slow";
        };
        var fast = (SupplierWithDelay<String>) () -> "fast";

        String result = RacingTasks.firstOf(List.of(slow, fast), Duration.ofSeconds(1));
        assertThat(result).isEqualTo("fast");
    }

    @FunctionalInterface
    interface SupplierWithDelay<T> extends java.util.function.Supplier<T> { }
}
