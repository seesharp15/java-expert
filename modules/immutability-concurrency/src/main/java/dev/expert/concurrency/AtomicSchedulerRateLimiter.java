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
            permits.reset();
            permits.add(permitsPerSecond);
        }, 1,1, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryAcquire() {
        while(true) {
            var current = permits.sum();
            if (current <= 0) return false;
            permits.decrement();
            return true;
        }
    }

    @Override
    public void close() {
        scheduler.shutdownNow();
    }
}

/*
ANSWER KEY:

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
