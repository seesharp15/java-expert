package leetcode.meta_prep.LC621;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LC621Test {

    private Solution getSolution() {
        return new Solution1(); // swap to Solution1 for your attempt
    }

    @Test
    void example1() {
        assertEquals(8, getSolution().leastInterval(new char[]{'A','A','A','B','B','B'}, 2));
    }

    @Test
    void zeroCooldownIsJustLength() {
        assertEquals(6, getSolution().leastInterval(new char[]{'A','A','A','B','B','B'}, 0));
    }

    @Test
    void exhibitsCorrectSpacing() {
        //'Z', A, B, C, D, 'Z', A, B, C, REST,'Z', A, B, REST, REST, 'Z', 'A', REST, REST, REST, 'Z'
        assertEquals(21, getSolution().leastInterval(new char[]{'Z','Z','Z','Z','Z', 'A','A','A','A','B','B','B','C','C','D'}, 4));
    }

    @Test
    void exhibitsCorrectSpacing2() {
        //'Z', A, B, C, D, 'Z', A, B, C, REST,'Z', A, B, REST, REST, 'Z', 'A', REST, REST, REST, 'Z'
        //X,Z,A,B,C,D
        //X,Z,A,B,C
        //X,Z,A,B, REST
        //X,Z,A,REST,REST
        //X,Z,REST,REST,REST
        //X
        assertEquals(26, getSolution().leastInterval(new char[]{'X','X','X','X','X','X','Z','Z','Z','Z','Z', 'A','A','A','A','B','B','B','C','C','D'}, 4));
    }

    //MAX LETTER COUNT (15) / DISTINCT CHARS (5)

    @Test
    void allSameTaskWithCooldownCreatesIdles() {
        assertEquals(9, getSolution().leastInterval(new char[]{'A','A','A'}, 3));
    }

    @Test
    void manyDistinctTasksIgnoreCooldown() {
        assertEquals(3, getSolution().leastInterval(new char[]{'A','B','C'}, 5));
    }

    @Test
    void multipleTasksShareMaxFrequency() {
        assertEquals(8, getSolution().leastInterval(new char[]{'A','A','A','B','B','B','C','C'}, 2));
    }

    @Test
    void pendingTasksFillEmptySlots() {
        assertEquals(7, getSolution().leastInterval(new char[]{'A','A','A','B','B','C','C'}, 2));
    }

    @Test
    void cooldownLargeButEnoughDiversity() {
        assertEquals(6, getSolution().leastInterval(new char[]{'A','A','B','C','D','E'}, 2));
    }

    @Test
    void emptyTasksReturnZero() {
        assertEquals(0, getSolution().leastInterval(new char[]{}, 3));
    }
}
