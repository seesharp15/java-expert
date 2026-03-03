package dev.expert.interview;

import java.util.*;

/** Problem 2: Top K frequent words. */
public final class TopKFrequent {
    private TopKFrequent() {}

    /**
     * Return k most frequent words, frequency desc, lexicographic asc for ties.
     */
    public static List<String> topK(List<String> words, int k) {
        throw new UnsupportedOperationException("TODO");
    }
}


















































/*
ANSWER KEY:
Problem: Return k most frequent words, frequency desc, lexicographic asc for ties.
Approach: Count with HashMap, then use PriorityQueue (min-heap) of size k with comparator (freq asc, lex desc) and finally pop to list and reverse.
Why: O(n log k) memory efficient; tie-breaker handled by comparator.

public static List<String> topK(List<String> words, int k) {
    Map<String, Integer> freq = new HashMap<>();
    for (var w : words) freq.merge(w, 1, Integer::sum);
    Comparator<String> cmp = (a,b) -> {
        int fa = freq.get(a), fb = freq.get(b);
        if (fa != fb) return fa - fb;          // min-heap by frequency
        return b.compareTo(a);                 // for same freq, lexicographically larger is "smaller" in heap
    };
    PriorityQueue<String> pq = new PriorityQueue<>(cmp);
    for (var w : freq.keySet()) {
        pq.offer(w);
        if (pq.size() > k) pq.poll();
    }
    List<String> res = new ArrayList<>();
    while (!pq.isEmpty()) res.add(pq.poll());
    Collections.reverse(res); // now highest freq first, lex asc
    return res;
}
*/
