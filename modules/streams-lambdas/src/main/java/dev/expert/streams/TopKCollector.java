package dev.expert.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

public final class TopKCollector {
    private TopKCollector() {}

    public static <T> Collector<T, ?, List<T>> topK(int k, Comparator<? super T> comparator) {
        throw new UnsupportedOperationException("TODO: implement topK collector retaining order by comparator");
    }
}

























































/*
ANSWER KEY:

public static <T> Collector<T, ?, List<T>> topK(int k, Comparator<? super T> comparator) {
    return Collector.of(
        () -> new java.util.PriorityQueue<T>(k, comparator),
        (pq, t) -> {
            pq.offer(t);
            if (pq.size() > k) pq.poll();
        },
        (a, b) -> {
            b.forEach(t -> { a.offer(t); if (a.size() > k) a.poll(); });
            return a;
        },
        pq -> {
            var list = new java.util.ArrayList<T>(pq);
            list.sort(comparator.reversed());
            return list;
        }
    );
}
*/
