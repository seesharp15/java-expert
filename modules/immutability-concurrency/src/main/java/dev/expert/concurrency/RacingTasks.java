package dev.expert.concurrency;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

public final class RacingTasks {
    private RacingTasks() {}

    /**
     * Run suppliers and return the first successful result, cancelling the rest.
     * Implement twice: (a) using classic executors, (b) optionally using virtual threads if enabled.
     */
    public static <T> T firstOf(List<Supplier<T>> suppliers, Duration timeout) {
        var executor = defaultExecutor();
        var service = new ExecutorCompletionService<T>(executor);
        var futures = new ArrayList<Future<T>>();

        for(var supplier: suppliers) {
            futures.add(service.submit(supplier::get));
        }

        try {
            var f = service.poll(timeout.toMillis(), TimeUnit.MILLISECONDS);
            if (f == null) throw new TimeoutException();
            T result = f.get();
            for(var other : futures) {
                if (other != f) {
                    other.cancel(true);
                }
            }
            return result;
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally{
            executor.shutdownNow();
        }
    }

    public static ExecutorService defaultExecutor() {
        return Executors.newFixedThreadPool(Math.max(4, Runtime.getRuntime().availableProcessors()));
    }
}


/*
ANSWER KEY:

public static <T> T firstOf(List<Supplier<T>> suppliers, Duration timeout) {
    ExecutorService exec = defaultExecutor();
    var service = new java.util.concurrent.ExecutorCompletionService<T>(exec);
    var futures = new java.util.ArrayList<java.util.concurrent.Future<T>>();
    for (Supplier<T> s : suppliers) {
        futures.add(service.submit(s::get));
    }
    try {
        java.util.concurrent.Future<T> f = service.poll(timeout.toMillis(), TimeUnit.MILLISECONDS);
        if (f == null) throw new java.util.concurrent.TimeoutException();
        T result = f.get();
        for (var other : futures) if (other != f) other.cancel(true);
        return result;
    } catch (Exception e) {
        throw new RuntimeException(e);
    } finally {
        exec.shutdownNow();
    }
}
*/
