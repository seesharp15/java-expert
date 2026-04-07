package gotham.asset.mgmt.GAM16;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GAM16Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Answer to verify
    }

    @Test
    void testReturnsABEGH() {
        assertEquals("ABEGH", getSolution().exceptionMaze());
    }

    @Test
    void testStartsWithA() {
        assertTrue(getSolution().exceptionMaze().startsWith("A"));
    }

    @Test
    void testContainsB() {
        assertTrue(getSolution().exceptionMaze().contains("B"));
    }

    @Test
    void testContainsInnerFinally() {
        // E comes from inner finally block
        assertTrue(getSolution().exceptionMaze().contains("E"));
    }

    @Test
    void testContainsOuterCatch() {
        // G comes from outer catch block
        assertTrue(getSolution().exceptionMaze().contains("G"));
    }

    @Test
    void testContainsOuterFinally() {
        // H comes from outer finally block
        assertTrue(getSolution().exceptionMaze().contains("H"));
    }

    @Test
    void testLengthIsFive() {
        assertEquals(5, getSolution().exceptionMaze().length());
    }

    @Test
    void testConsistentResult() {
        // Multiple invocations should return the same result
        String first = getSolution().exceptionMaze();
        String second = getSolution().exceptionMaze();
        assertEquals(first, second);
    }
}
