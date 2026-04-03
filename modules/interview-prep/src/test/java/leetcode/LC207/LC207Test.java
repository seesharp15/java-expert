package leetcode.LC207;

import leetcode.LC207.Solution;
import leetcode.LC207.Solution3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LC207Test {

    private Solution getSolution() {
        return new Solution5(); // swap implementations here
    }

    @Test
    void canFinish_singleCourseNoPrereqs_returnsTrue() {
        int numCourses = 1;
        int[][] prerequisites = {};

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_multipleCoursesNoPrereqs_returnsTrue() {
        int numCourses = 5;
        int[][] prerequisites = {};

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_simpleChain_returnsTrue() {
        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0},
                {2, 1},
                {3, 2}
        };

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_complexChain_returnsTrue() {
        int numCourses = 7;
        int[][] prerequisites = {
                {1, 0},
                {2, 0},
                {3, 0},
                {3, 2},
                {3, 6},
                {6, 7},
                {7, 8},
                {8, 1}
        };

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_simpleCycle_returnsFalse() {
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0},
                {0, 1}
        };

        assertFalse(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_threeNodeCycle_returnsFalse() {
        int numCourses = 3;
        int[][] prerequisites = {
                {1, 0},
                {2, 1},
                {0, 2}
        };

        assertFalse(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_disconnectedGraphWithoutCycle_returnsTrue() {
        int numCourses = 6;
        int[][] prerequisites = {
                {1, 0},
                {3, 2},
                {5, 4}
        };

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_disconnectedGraphWithCycleInOneComponent_returnsFalse() {
        int numCourses = 6;
        int[][] prerequisites = {
                {1, 0},
                {0, 1},   // cycle here
                {3, 2},
                {5, 4}
        };

        assertFalse(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_multiplePrereqsForOneCourse_returnsTrue() {
        int numCourses = 4;
        int[][] prerequisites = {
                {3, 1},
                {3, 2},
                {1, 0},
                {2, 0}
        };

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_diamondDependency_returnsTrue() {
        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_courseDependsOnItself_returnsFalse() {
        int numCourses = 3;
        int[][] prerequisites = {
                {1, 1}
        };

        assertFalse(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_largerAcyclicGraph_returnsTrue() {
        int numCourses = 8;
        int[][] prerequisites = {
                {1, 0},
                {2, 0},
                {3, 1},
                {4, 1},
                {5, 2},
                {6, 3},
                {6, 4},
                {7, 5}
        };

        assertTrue(getSolution().canFinish(numCourses, prerequisites));
    }

    @Test
    void canFinish_largerGraphWithCycle_returnsFalse() {
        int numCourses = 8;
        int[][] prerequisites = {
                {1, 0},
                {2, 1},
                {3, 2},
                {4, 3},
                {5, 4},
                {2, 5}   // creates cycle: 2 -> 3 -> 4 -> 5 -> 2
        };

        assertFalse(getSolution().canFinish(numCourses, prerequisites));
    }

}
