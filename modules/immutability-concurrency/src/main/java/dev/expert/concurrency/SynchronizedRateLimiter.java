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
        var now = System.nanoTime();
        var window = 1_000_000_000L;
        while(!timestamps.isEmpty() && now - timestamps.peekFirst() >= window) {
            timestamps.removeFirst();
        }

        if (timestamps.size() < permitsPerSecond) {
            timestamps.addLast(now);
            return true;
        }
        return false;
    }

    @Override
    public void close() {
        // nothing to close here
    }
}




/*
ANSWER KEY:

 * Problem: rate-limit calls to N per second using basic locking.
 * Approach: token bucket implemented as timestamp deque; prune stale entries then grant if under capacity.
 * Why: shows baseline thread-safe limiter with intrinsic lock.

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
