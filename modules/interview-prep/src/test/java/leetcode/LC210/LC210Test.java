package leetcode.LC210;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

public class LC210Test {

    private final Solution solution = new Solution3();

    @Test
    void example1_returnsValidOrder() {
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertValidTopologicalOrder(numCourses, prerequisites, result);
    }

    @Test
    void example2_returnsValidOrder() {
        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertValidTopologicalOrder(numCourses, prerequisites, result);
    }

    @Test
    void noPrerequisites_returnsAllCourses() {
        int numCourses = 4;
        int[][] prerequisites = {};

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertEquals(numCourses, result.length);
        assertContainsExactlyCourses(numCourses, result);
        assertValidTopologicalOrder(numCourses, prerequisites, result);
    }

    @Test
    void singleCourse_noPrerequisites() {
        int numCourses = 1;
        int[][] prerequisites = {};

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertArrayEquals(new int[] {0}, result);
    }

    @Test
    void cycle_returnsEmptyArray() {
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0},
                {0, 1}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertEquals(0, result.length);
    }

    @Test
    void longerCycle_returnsEmptyArray() {
        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0},
                {2, 1},
                {3, 2},
                {1, 3}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertEquals(0, result.length);
    }

    @Test
    void disconnectedGraph_returnsValidOrderAcrossAllComponents() {
        int numCourses = 6;
        int[][] prerequisites = {
                {1, 0},
                {3, 2},
                {5, 4}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertValidTopologicalOrder(numCourses, prerequisites, result);
    }

    @Test
    void duplicatePrerequisites_stillReturnsValidOrder() {
        int numCourses = 3;
        int[][] prerequisites = {
                {1, 0},
                {1, 0},
                {2, 1}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertValidTopologicalOrder(numCourses, prerequisites, result);
    }

    @Test
    void diamondDependency_returnsValidOrder() {
        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0},
                {2, 0},
                {3, 1},
                {3, 2}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertValidTopologicalOrder(numCourses, prerequisites, result);
    }

    @Test
    void allCoursesChained_returnsExactLinearOrder() {
        int numCourses = 5;
        int[][] prerequisites = {
                {1, 0},
                {2, 1},
                {3, 2},
                {4, 3}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertArrayEquals(new int[] {0, 1, 2, 3, 4}, result);
    }

    @Test
    void prerequisiteOnIsolatedAndConnectedNodes_returnsValidOrder() {
        int numCourses = 5;
        int[][] prerequisites = {
                {2, 1},
                {3, 1}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertValidTopologicalOrder(numCourses, prerequisites, result);
    }

    @Test
    void selfDependency_returnsEmptyArray() {
        int numCourses = 3;
        int[][] prerequisites = {
                {0, 0}
        };

        int[] result = solution.findOrder(numCourses, prerequisites);

        assertEquals(0, result.length);
    }

    private void assertValidTopologicalOrder(int numCourses, int[][] prerequisites, int[] order) {
        assertNotNull(order, "Returned order should not be null");
        assertEquals(numCourses, order.length, "Order should contain every course exactly once");
        assertContainsExactlyCourses(numCourses, order);

        Map<Integer, Integer> indexByCourse = new HashMap<>();
        for (int i = 0; i < order.length; i++) {
            indexByCourse.put(order[i], i);
        }

        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int prerequisite = prereq[1];

            assertTrue(
                    indexByCourse.get(prerequisite) < indexByCourse.get(course),
                    "Prerequisite " + prerequisite + " must come before course " + course
            );
        }
    }

    private void assertContainsExactlyCourses(int numCourses, int[] order) {
        boolean[] seen = new boolean[numCourses];

        for (int course : order) {
            assertTrue(course >= 0 && course < numCourses, "Course out of range: " + course);
            assertFalse(seen[course], "Duplicate course found: " + course);
            seen[course] = true;
        }

        for (int i = 0; i < numCourses; i++) {
            assertTrue(seen[i], "Missing course: " + i);
        }
    }
}