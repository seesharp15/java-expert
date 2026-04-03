package dev.expert.interview;

import java.util.*;


/**
 * Problem 1: Implement an LRU cache with O(1) get/put. Follow-up: make thread-safe.
 */
public class LruCache {
    private final int capacity;

    private final Map<Integer, Integer> map;
    // TODO: choose an O(1) data structure (likely LinkedHashMap or DLL+HashMap)

    public LruCache(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be >= 1");
        this.capacity = capacity;
        map = new LinkedHashMap<>(this.capacity) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    /**
     * Return value for key, or -1 if missing. Should mark as most recently used.
     */
    public synchronized int get(int key) {
        return map.getOrDefault(key, -1);
    }

    /**
     * Insert or update value for key. Evict least recently used when over capacity.
     */
    public void put(int key, int value) {
        map.put(key, value);
    }

    /** Optional: return current size (for tests/debugging). */
    public int size() {
        return map.size();
    }
}



/*
ANSWER KEY:
Problem: O(1) LRU cache with get/put and eviction.
Approach: LinkedHashMap with accessOrder=true gives O(1) recency tracking; override removeEldestEntry.
Why: Simplest correct solution in Java SDK; thread-safety can wrap with synchronized blocks or Collections.synchronizedMap.

public class LruCache {
    private final Map<Integer, Integer> map;
    public LruCache(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("capacity must be >= 1");
        this.map = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }
    public synchronized int get(int key) { // synchronize for thread-safe follow-up
        return map.getOrDefault(key, -1);
    }
    public synchronized void put(int key, int value) { map.put(key, value); }
    public synchronized int size() { return map.size(); }
}
*/
