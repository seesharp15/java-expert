package dev.expert.interview.GraphProblems;


import java.util.*;

/*
*     @Test
    void findsShortestPath() {
        Map<Integer, List<Integer>> graph = Map.of(
            1, List.of(2,3),
            2, List.of(4),
            3, List.of(4,5),
            4, List.of(6),
            5, List.of(6),
            6, List.of()
        );
        assertThat(GraphProblems2.shortestPath(graph, 1, 6)).containsExactly(1,2,4,6);
    }
* */
/** Problem 4: BFS shortest path in unweighted graph. */
public final class GraphProblems3 {
    private GraphProblems3() {}

    /** Return list of node ids from start to end inclusive; empty if none. */
    public static List<Integer> shortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
        if(start == end) return List.of(start);

        var visited = new LinkedHashMap<Integer, Integer>();

        var q = new ArrayDeque<Integer>();
        q.add(start);
        visited.put(start, null);


        while(!q.isEmpty()) {
            var key = q.remove();

            //for(var v = 0; v <= graph.getOrDefault(key, List.of())).size();v++){

            for(var v : graph.getOrDefault(key, List.of())) {
                if (!visited.containsKey(v)) {
                    visited.put(v, key);
                    if (v == end) return buildMap(visited, end);
                    q.add(v);
                }


            }

        }
        return List.of();
    }

    private static List<Integer> buildMap(LinkedHashMap<Integer, Integer> visited, int end) {
        var path = new LinkedList<Integer>();

        for(Integer n = end; n != null; n = visited.get(n)) {
            path.addFirst(n);
        }
        return path;
        //
//        List<Integer> path = new ArrayList<Integer>();
//
//        Integer current = end;
//
//        while(current != null) {
//
//            current = visited.get(current);
//        }

    }
}


