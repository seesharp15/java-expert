package dev.expert.concurrency;

import java.time.Duration;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * Uses an atomic counter scheduled refill suitable for virtual threads / Loom friendly tasks.
 */
public class AtomicSchedulerRateLimiter implements RateLimiter {
    private final int permitsPerSecond;
    private final LongAdder permits = new LongAdder();
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public AtomicSchedulerRateLimiter(int permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
        permits.add(permitsPerSecond);
        scheduler.scheduleAtFixedRate(() -> {
            // TODO refill logic
        }, 1,1, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryAcquire() {
        throw new UnsupportedOperationException("TODO: implement atomic scheduler limiter");
    }

    @Override
    public void close() {
        scheduler.shutdownNow();
    }
}

/*
ANSWER KEY:

 * Problem: limiter suited for high contention without locking.
 * Approach: LongAdder counts available permits; scheduled task resets bucket every second.
 * Why: illustrates atomics + scheduled executor pattern (Loom friendly).

public AtomicSchedulerRateLimiter(int permitsPerSecond) {
    this.permitsPerSecond = permitsPerSecond;
    permits.add(permitsPerSecond);
    scheduler.scheduleAtFixedRate(() -> {
        permits.reset();
        permits.add(permitsPerSecond);
    }, 1, 1, TimeUnit.SECONDS);
}

@Override
public boolean tryAcquire() {
    while (true) {
        long current = permits.sum();
        if (current <= 0) return false;
        permits.decrement();
        return true;
    }
}
*/
