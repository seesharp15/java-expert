package dev.expert.interview.GraphProblems;

import java.sql.Array;
import java.util.*;

/** Problem 4: BFS shortest path in unweighted graph. */
public final class GraphProblems2 {
    private GraphProblems2() {
    }


    public static List<Integer> shortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
        if (start == end) return List.of(start);

        //map to hold all visited locations
        //queue to hold locations not yet visited

        //while items are in the queue
        //for each edge in edges
        //visited check
        //if edge == destination, build map and return
        //add edge to queue


        Map<Integer, Integer> visited = new HashMap<>();
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited.put(start, null);

        while (!queue.isEmpty()) {
            var current = queue.remove();
            for (var v : graph.getOrDefault(current, List.of())) {
                if (visited.containsKey(v)) continue;

                visited.put(v, current);
                if (v == end) return buildShortestPath(visited, end);
                queue.add(v);
            }

        }

        return List.of();
    }

    private static List<Integer> buildShortestPath(Map<Integer, Integer> visited, int end) {
        List<Integer> path = new ArrayList<>();
        Integer current = end;
        while (current != null) {
            path.add(current);
            current = visited.get(current);
        }
        Collections.reverse(path);
        return path;
    }


}












//
//    public static List<Integer> shortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
//
//        if (start == end) return List.of(start);
//
//        Map<Integer, Integer> visited = new HashMap<>();
//        Queue<Integer> queue = new ArrayDeque<>();
//
//        queue.add(start);
//        visited.put(start, null);
//
//        while(!queue.isEmpty()) {
//            var current = queue.remove();
//            for(var v: graph.getOrDefault(current, List.of())){
//                if (visited.containsKey(v)) continue;
//                visited.put(v, current);
//                if (v == end) return build(visited, end);
//                queue.add(v);
//            }
//        }
//
//    }
//
    /** Return list of node ids from start to end inclusive; empty if none. */
//    public static List<Integer> shortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
//
//        if (start == end) return List.of(start);
//
//        Map<Integer, Integer> parent = new HashMap<>();
//        Queue<Integer> queue = new ArrayDeque<>();
//
//        queue.add(start);
//        parent.put(start, null);
//
//        while(!queue.isEmpty()) {
//            var u = queue.remove();
//            for(int v: graph.getOrDefault(u, List.of())) {
//                if (parent.containsKey(v)) {
//                    continue;
//                }
//                parent.put(v, u);
//                if (v == end) return build(parent, end);
//                queue.add(v);
//            }
//        }
//
//
//
//    }
//
//    private static List<Integer> build(Map<Integer, Integer> parent, int end) {
//        List<Integer> path = new ArrayList<>();
//        Integer current = end;
//        while(current != null) {
//            path.add(current);
//            current = parent.get(current);
//        }
//        Collections.reverse(path);
//        return path;
//    }
//}