package gotham.asset.mgmt.GAM11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM11Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testNegative() {
        assertEquals("negative", getSolution().classify(-5));
    }

    @Test
    void testZero() {
        assertEquals("zero", getSolution().classify(0));
    }

    @Test
    void testOdd() {
        assertEquals("odd", getSolution().classify(7));
    }

    @Test
    void testSmallEven() {
        assertEquals("small even", getSolution().classify(4));
    }

    @Test
    void testSmallEvenBoundary() {
        assertEquals("small even", getSolution().classify(2));
    }

    @Test
    void testMediumEven() {
        assertEquals("medium even", getSolution().classify(42));
    }

    @Test
    void testMediumEvenLowerBoundary() {
        assertEquals("medium even", getSolution().classify(10));
    }

    @Test
    void testLargeEven() {
        assertEquals("large even", getSolution().classify(100));
    }
}
