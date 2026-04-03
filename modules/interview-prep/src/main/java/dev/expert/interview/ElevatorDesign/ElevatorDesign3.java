package dev.expert.interview.ElevatorDesign;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/** Problem 7: Simplified elevator design sketch in code. */
public class ElevatorDesign3 {

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

        //three phased approach
            //all going up >= currentFloor
            //all going down
            //all going up <= currentFloor


        //sorted by phase, floor (either descending or ascending)

        var sorted = requests.stream()
                .sorted(Comparator.<Request>comparingInt(i -> getPhase(currentFloor, i.floor, i.direction))
                        .thenComparingInt(i -> i.floor * sortKey(i.direction))
                ).map(r -> r.floor).toList();


        return sorted;




    }

    private static int getPhase(int currentFloor, int requestedFloor, Direction d) {
        return switch (d) {
            case Direction.UP -> requestedFloor >= currentFloor ? 1 : 3;
            case Direction.DOWN -> 2;
            default -> -1;
        };
    }

    private static int sortKey(Direction d) {
        return d == Direction.UP ? 1 : -1;
    }

}


