package leetcode.LC286;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class LC286Tests {

    private static final int INF = Integer.MAX_VALUE;
    private final Solution solution = new Solution3();

    @Test
    void exampleCase() {
        int[][] rooms = {
                {INF , -1  , 0    , INF},
                {INF , INF , INF  , -1},
                {INF , -1  , INF  , -1},
                {0   , -1  , INF  , INF}
        };

        int[][] expected = {
                {3, -1, 0, 1},
                {2, 2, 1, -1},
                {1, -1, 2, -1},
                {0, -1, 3, 4}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void emptyGrid() {
        int[][] rooms = {};

        solution.wallsAndGates(rooms);

        assertEquals(0, rooms.length);
    }

    @Test
    void singleGate() {
        int[][] rooms = {
                {0}
        };

        int[][] expected = {
                {0}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void singleWall() {
        int[][] rooms = {
                {-1}
        };

        int[][] expected = {
                {-1}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void singleEmptyRoomNoGate() {
        int[][] rooms = {
                {INF}
        };

        int[][] expected = {
                {INF}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void oneRow() {
        int[][] rooms = {
                {0, INF, INF, -1, INF}
        };

        int[][] expected = {
                {0, 1, 2, -1, INF}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void oneColumn() {
        int[][] rooms = {
                {INF},
                {INF},
                {0},
                {-1},
                {INF}
        };

        int[][] expected = {
                {2},
                {1},
                {0},
                {-1},
                {INF}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void allWalls() {
        int[][] rooms = {
                {-1, -1},
                {-1, -1}
        };

        int[][] expected = {
                {-1, -1},
                {-1, -1}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void allGates() {
        int[][] rooms = {
                {0, 0},
                {0, 0}
        };

        int[][] expected = {
                {0, 0},
                {0, 0}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void unreachableRoomsStayInf() {
        int[][] rooms = {
                {0, -1, INF},
                {-1, -1, INF},
                {INF, INF, INF}
        };

        int[][] expected = {
                {0, -1, INF},
                {-1, -1, INF},
                {INF, INF, INF}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void choosesNearestGate() {
        int[][] rooms = {
                {0, INF, INF, 0},
                {INF, INF, INF, INF},
                {INF, INF, INF, INF}
        };

        int[][] expected = {
                {0, 1, 1, 0},
                {1, 2, 2, 1},
                {2, 3, 3, 2}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void wallsForceLongerPath() {
        int[][] rooms = {
                {0, -1, INF, INF},
                {INF, -1, INF, -1},
                {INF, INF, INF, -1},
                {INF, -1, INF, INF}
        };

        int[][] expected = {
                {0, -1, 6, 7},
                {1, -1, 5, -1},
                {2, 3, 4, -1},
                {3, -1, 5, 6}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    @Test
    void gateSurroundedByReachableRooms() {
        int[][] rooms = {
                {INF, INF, INF},
                {INF, 0, INF},
                {INF, INF, INF}
        };

        int[][] expected = {
                {2, 1, 2},
                {1, 0, 1},
                {2, 1, 2}
        };

        solution.wallsAndGates(rooms);

        assertGridEquals(expected, rooms);
    }

    private void assertGridEquals(int[][] expected, int[][] actual) {
        assertEquals(expected.length, actual.length, "Row count mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch at row " + i);
        }
    }
}
