package dev.expert.concurrency;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Token-bucket style limiter using intrinsic locks. Capacity = permitsPerSecond.
 */
public class SynchronizedRateLimiter implements RateLimiter {
    private final int permitsPerSecond;
    private final Deque<Long> timestamps = new ArrayDeque<>();

    public SynchronizedRateLimiter(int permitsPerSecond) {
        this.permitsPerSecond = permitsPerSecond;
    }

    @Override
    public synchronized boolean tryAcquire() {
        throw new UnsupportedOperationException("TODO: implement synchronized token bucket");
    }

    @Override
    public void close() {
        // nothing to close here
    }
}

























































/*
ANSWER KEY:

@Override
public synchronized boolean tryAcquire() {
    long now = System.nanoTime();
    long window = 1_000_000_000L;
    while (!timestamps.isEmpty() && now - timestamps.peekFirst() >= window) {
        timestamps.removeFirst();
    }
    if (timestamps.size() < permitsPerSecond) {
        timestamps.addLast(now);
        return true;
    }
    return false;
}
*/
