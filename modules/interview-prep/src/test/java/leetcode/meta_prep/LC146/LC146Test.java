package leetcode.meta_prep.LC146;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC146Test {

    private Solution getCache(int capacity) {
        return new Solution1(capacity); // swap to LRUCache1 when ready
    }

    @Test
    void basicPutGet() {
        var cache = getCache(2);
        cache.put(1,1);
        cache.put(2,2);
        assertEquals(1, cache.get(1));
    }

    @Test
    void evictsLru() {
        var cache = getCache(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3); // evicts key 1
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    void updateMovesToFront() {
        var cache = getCache(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(1,10); // refresh 1
        cache.put(3,3); // evict 2
        assertEquals(-1, cache.get(2));
        assertEquals(10, cache.get(1));
    }
}
