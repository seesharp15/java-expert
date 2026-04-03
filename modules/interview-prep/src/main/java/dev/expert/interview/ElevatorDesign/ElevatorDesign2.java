package dev.expert.interview.ElevatorDesign;

import java.util.Comparator;
import java.util.List;

public class ElevatorDesign2 {

    public enum Direction {UP, DOWN, IDLE}

    public static class Request {
        public final int floor;
        public final Direction direction;

        public Request(int floor, Direction direction) {
            this.floor = floor;
            this.direction = direction;
        }
    }

    /**
     * Given current floor and a list of pickup requests, compute a simple scheduling order (e.g., SCAN).
     * Return list of floors to visit in order.
     */


    public List<Integer> schedule(int currentFloor, List<Request> requests) {
        Comparator<Request>  comparator = Comparator.<Request>comparingInt(r -> phase(r, currentFloor))
                .thenComparingInt(ElevatorDesign2::phaseSortKey);

        return requests.stream().sorted(comparator).map(r -> r.floor).toList();
    }

    private static int phase(Request r, int currentFloor) {
        if (r.direction == Direction.UP && r.floor >= currentFloor) return 0;
        if (r.direction == Direction.DOWN) return 1;
        return 2;
    }
    private static int phaseSortKey(Request r) {
        return (r.direction == Direction.DOWN) ? -r.floor : r.floor;
    }
}