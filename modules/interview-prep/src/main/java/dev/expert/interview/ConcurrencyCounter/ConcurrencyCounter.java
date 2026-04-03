package dev.expert.interview.ConcurrencyCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/** Problem 5: Multi-threaded counter correctness. */
public class ConcurrencyCounter {

    /**
     * Start {@code threads} threads, each increments shared counter {@code perThread} times.
     * Return final counter value. Implement with a correct concurrency primitive.
     */
    public static int runCounter(int threadCount, int perThread) {

        AtomicInteger counter = new AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for(var i = 0; i < threadCount; i++) {
            var thread = new Thread(() -> {
                for (var c = 0; c < perThread; c++) {
                    counter.incrementAndGet();
                }
            });
            thread.start();
            threads.add(thread);
        }

        for(var t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return counter.get();
    }
}


















































/*
ANSWER KEY:
Problem: increment a shared counter concurrently and get exact result.
Approach: use AtomicInteger (or synchronized block) and join threads.
Why volatile alone is insufficient: it doesn't provide atomicity.

public static int runCounter(int threads, int perThread) {
    AtomicInteger counter = new AtomicInteger();
    Thread[] ts = new Thread[threads];
    for (int i = 0; i < threads; i++) {
        ts[i] = new Thread(() -> {
            for (int j = 0; j < perThread; j++) counter.incrementAndGet();
        });
        ts[i].start();
    }
    for (Thread t : ts) {
        try { t.join(); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
    return counter.get();
}
*/
