package dev.expert.interview;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LruCacheTest {

    @Test
    void evictsLeastRecentlyUsed() {
        LruCache cache = new LruCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assertThat(cache.get(1)).isEqualTo(1); // 1 is MRU now
        cache.put(3, 3); // evicts key 2
        assertThat(cache.get(2)).isEqualTo(-1);
        cache.put(4, 4); // evicts key 1
        assertThat(cache.get(1)).isEqualTo(-1);
        assertThat(cache.get(3)).isEqualTo(3);
        assertThat(cache.get(4)).isEqualTo(4);
    }

    @Test
    void updatesExistingKeyAndMovesToMru() {
        LruCache cache = new LruCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(1, 11);
        cache.put(3, 30); // should evict key 2 now
        assertThat(cache.get(1)).isEqualTo(11);
        assertThat(cache.get(2)).isEqualTo(-1);
    }
}
