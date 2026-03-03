package dev.expert.concurrency;

import java.time.Duration;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.StampedLock;

public class StampedLockRateLimiter implements RateLimiter {
    private final int permitsPerSecond;
    private final Deque<Long> timestamps = new ArrayDeque<>();
    private final StampedLock lock = new StampedLock();

    public StampedLockRateLimiter(int permitsPerSecond) {

        this.permitsPerSecond = permitsPerSecond;

    }

    @Override
    public boolean tryAcquire() {
        var now = System.nanoTime();
        var window = (long)10e9;
        var stamp = lock.tryOptimisticRead();
        if (stamp != 0L) {
            prune(now, window, stamp);
            if (timestamps.size() < permitsPerSecond) {
                timestamps.addLast(now);
                return true;
            }
        }
        return false;
    }

    private void prune(long now, long window, long stamp) {
        while(!timestamps.isEmpty()) {
            var first = timestamps.peekFirst();
            if (first == null || now - first < window) {
                break;
            }
            if (!lock.validate(stamp)) {
                return;
            }
            timestamps.removeFirst();

        }
    }


    @Override
    public void close() {
        // nothing to close
    }
}

/*
ANSWER KEY:

 * Problem: same token-bucket limiter but with finer-grained StampedLock.
 * Approach: optimistic read to prune cheap, escalate to write lock to mutate.
 * Why: demonstrates lock upgrades and optimistic validation.

@Override
public boolean tryAcquire() {
    long now = System.nanoTime();
    long window = 1_000_000_000L;

    long stamp = lock.tryOptimisticRead();
    if (stamp != 0L) {
        prune(now, window, stamp);
    }

    stamp = lock.writeLock();
    try {
        prune(now, window, stamp);
        if (timestamps.size() < permitsPerSecond) {
            timestamps.addLast(now);
            return true;
        }
        return false;
    } finally {
        lock.unlockWrite(stamp);
    }
}

private void prune(long now, long window, long stamp) {
    while (!timestamps.isEmpty()) {
        Long first = timestamps.peekFirst();
        if (first == null || now - first < window) break;
        if (!lock.validate(stamp)) return; // optimistic read invalid
        timestamps.removeFirst();
    }
}
*/
