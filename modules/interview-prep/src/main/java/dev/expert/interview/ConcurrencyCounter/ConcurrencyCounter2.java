package dev.expert.interview.ConcurrencyCounter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyCounter2 {

    /**
     * Start {@code threads} threads, each increments shared counter {@code perThread} times.
     * Return final counter value. Implement with a correct concurrency primitive.
     */
    public static int runCounter(int threadCount, int perThread) {

        var threads = new ArrayList<Thread>();
        var counter = new AtomicInteger();

        for(var i = 0; i < threadCount; i++) {
            var thread =  new Thread(() -> {
                for(var pt = 0; pt < perThread; pt++) {
                    counter.incrementAndGet();
                }
            });
            thread.start();
            threads.add(thread);
        }

        for(var thread: threads) {
            try{
                thread.join();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        return counter.get();
    }
}
