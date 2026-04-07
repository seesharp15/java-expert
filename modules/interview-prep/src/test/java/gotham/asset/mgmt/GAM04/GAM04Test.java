package gotham.asset.mgmt.GAM04;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class GAM04Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testAllPositive() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        int sum = getSolution().sumPositive(map);

        assertEquals(6, sum);
        assertEquals(3, map.size());
    }

    @Test
    void testRemovesNegatives() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", -2);
        map.put("c", 3);

        int sum = getSolution().sumPositive(map);

        assertEquals(4, sum);
        assertEquals(2, map.size());
        assertTrue(map.containsKey("a"));
        assertTrue(map.containsKey("c"));
        assertFalse(map.containsKey("b"));
    }

    @Test
    void testAllNegative() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", -1);
        map.put("b", -2);

        int sum = getSolution().sumPositive(map);

        assertEquals(0, sum);
        assertTrue(map.isEmpty());
    }

    @Test
    void testEmptyMap() {
        Map<String, Integer> map = new HashMap<>();

        int sum = getSolution().sumPositive(map);

        assertEquals(0, sum);
    }

    @Test
    void testWithZero() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 0);
        map.put("b", -1);
        map.put("c", 5);

        int sum = getSolution().sumPositive(map);

        assertEquals(5, sum);
        assertEquals(2, map.size());
        assertTrue(map.containsKey("a"));
        assertTrue(map.containsKey("c"));
        assertFalse(map.containsKey("b"));
    }

    @Test
    void testMapIsMutated() {
        Map<String, Integer> map = new HashMap<>();
        map.put("x", 10);
        map.put("y", -5);
        map.put("z", 20);
        map.put("w", -3);

        getSolution().sumPositive(map);

        assertEquals(2, map.size());
        assertTrue(map.containsKey("x"));
        assertTrue(map.containsKey("z"));
    }
}
