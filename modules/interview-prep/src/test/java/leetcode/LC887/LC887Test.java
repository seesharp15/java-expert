package leetcode.LC887;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC887Test {

    private Solution getSolution() {
        return new Solution(); // swap implementations here
    }

    @Test
    void handlesZeroFloors() {
        assertEquals(0, getSolution().superEggDrop(2, 0));
    }

    @Test
    void singleEgg_requiresLinearMoves() {
        assertEquals(5, getSolution().superEggDrop(1, 5));
    }

    @Test
    void sampleCase_twoEggsSixFloors_returnsThree() {
        assertEquals(3, getSolution().superEggDrop(2, 6));
    }

    @Test
    void sampleCase_threeEggsFourteenFloors_returnsFour() {
        assertEquals(4, getSolution().superEggDrop(3, 14));
    }

    @Test
    void twoEggsHundredFloors_returnsFourteen() {
        assertEquals(14, getSolution().superEggDrop(2, 100));
    }

    @Test
    void increasingEggsReducesMoves() {
        int movesWithTwo = getSolution().superEggDrop(2, 50);
        int movesWithThree = getSolution().superEggDrop(3, 50);
        assertTrue(movesWithThree <= movesWithTwo);
    }

    @Test
    void invalidEggCountThrows() {
        assertThrows(IllegalArgumentException.class, () -> getSolution().superEggDrop(0, 10));
    }
}
