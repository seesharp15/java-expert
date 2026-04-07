package gotham.asset.mgmt.GAM03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM03Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    /**
     * Reference implementation of the original nested loop algorithm.
     * Used to verify that the optimized solution produces identical results.
     */
    private int reference(int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                if (j > i + 1) break;
                total += (i + j);
            }
        }
        return total;
    }

    @Test
    void testN0() {
        assertEquals(reference(0), getSolution().compute(0));
        assertEquals(0, getSolution().compute(0));
    }

    @Test
    void testN1() {
        assertEquals(reference(1), getSolution().compute(1));
        assertEquals(0, getSolution().compute(1));
    }

    @Test
    void testN5() {
        assertEquals(reference(5), getSolution().compute(5));
        assertEquals(56, getSolution().compute(5));
    }

    @Test
    void testNegativeInput() {
        assertEquals(0, getSolution().compute(-1));
    }

    @Test
    void testMatchesReferenceForSmallValues() {
        for (int n : new int[]{0, 1, 2, 3, 4, 5, 10, 20}) {
            assertEquals(reference(n), getSolution().compute(n),
                    "Mismatch for n=" + n);
        }
    }

    @Test
    void testOutputs() {
        getSolution().compute(10);

    }
}
