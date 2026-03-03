package dev.expert.concurrency;

public interface RateLimiter extends AutoCloseable {
    boolean tryAcquire();
    @Override
    void close();
}
