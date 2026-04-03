package leetcode.LC146;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC146Test {

    private LRUCache getCache(int capacity) {
        return new LRUCache1(capacity);
    }

    @Test
    void example1() {
        var cache = getCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1)); // cache order: 2,1

        cache.put(3, 3); // evicts key 2
        assertEquals(-1, cache.get(2));

        cache.put(4, 4); // evicts key 1
        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    void getMissingKey_returnsMinusOne() {
        var cache = getCache(2);

        assertEquals(-1, cache.get(42));
    }

    @Test
    void putExistingKey_updatesValue() {
        var cache = getCache(2);

        cache.put(1, 1);
        cache.put(1, 10);

        assertEquals(10, cache.get(1));
    }

    @Test
    void putExistingKey_marksItMostRecentlyUsed() {
        var cache = getCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        cache.put(1, 10); // key 1 becomes MRU
        cache.put(3, 3);  // should evict key 2

        assertEquals(10, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    void getMarksItemAsMostRecentlyUsed() {
        var cache = getCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1)); // key 1 becomes MRU
        cache.put(3, 3); // should evict key 2

        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    void capacityOne_evictsCorrectly() {
        var cache = getCache(1);

        cache.put(1, 1);
        assertEquals(1, cache.get(1));

        cache.put(2, 2); // evicts key 1
        assertEquals(-1, cache.get(1));
        assertEquals(2, cache.get(2));
    }

    @Test
    void repeatedGets_doNotBreakOrder() {
        var cache = getCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        assertEquals(1, cache.get(1));
        assertEquals(1, cache.get(1));
        assertEquals(1, cache.get(1));

        cache.put(3, 3); // should still evict key 2

        assertEquals(1, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(3, cache.get(3));
    }

    @Test
    void updateThenEvict_usesLatestValue() {
        var cache = getCache(2);

        cache.put(2, 1);
        cache.put(2, 2);

        assertEquals(2, cache.get(2));

        cache.put(1, 1);
        cache.put(4, 1); // should evict least recently used

        assertEquals(-1, cache.get(2));
        assertEquals(1, cache.get(1));
        assertEquals(1, cache.get(4));
    }

    @Test
    void evictionAfterAccessSequence_isCorrect() {
        var cache = getCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);

        assertEquals(1, cache.get(1)); // order updates
        assertEquals(2, cache.get(2)); // order updates

        cache.put(4, 4); // should evict key 3

        assertEquals(1, cache.get(1));
        assertEquals(2, cache.get(2));
        assertEquals(-1, cache.get(3));
        assertEquals(4, cache.get(4));
    }

    @Test
    void manyOperations_staysConsistent() {
        var cache = getCache(2);

        cache.put(2, 1);
        cache.put(1, 1);
        cache.put(2, 3);
        cache.put(4, 1);

        assertEquals(-1, cache.get(1));
        assertEquals(3, cache.get(2));
    }
}
