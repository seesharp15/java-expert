package dev.expert.interview;

import java.util.*;

/** Problem 7: Simplified elevator design sketch in code. */
public class ElevatorDesign {

    public enum Direction { UP, DOWN, IDLE }

    public static class Request {
        public final int floor;
        public final Direction direction;
        public Request(int floor, Direction direction) { this.floor = floor; this.direction = direction; }
    }

    /**
     * Given current floor and a list of pickup requests, compute a simple scheduling order (e.g., SCAN).
     * Return list of floors to visit in order.
     */
    public List<Integer> schedule(int currentFloor, List<Request> requests) {
        throw new UnsupportedOperationException("TODO");
    }
}


















































/*
ANSWER KEY:
Problem: simple elevator scheduler outputting visit order (not full simulator).
Approach: SCAN-like: split requests into above and below current floor; serve ascending then descending.
Why: deterministic, minimal backtracking, easy to reason about concurrency extensions.

public List<Integer> schedule(int currentFloor, List<Request> requests) {
    List<Integer> up = new ArrayList<>();
    List<Integer> down = new ArrayList<>();
    for (var r : requests) {
        if (r.floor >= currentFloor) up.add(r.floor); else down.add(r.floor);
    }
    up.sort(Integer::compareTo);
    down.sort((a,b) -> Integer.compare(b, a)); // descending
    List<Integer> order = new ArrayList<>();
    order.addAll(up);
    order.addAll(down);
    return order;
}
*/
