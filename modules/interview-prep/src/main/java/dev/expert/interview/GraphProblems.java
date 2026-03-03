package dev.expert.interview;

import java.util.*;

/** Problem 4: BFS shortest path in unweighted graph. */
public final class GraphProblems {
    private GraphProblems() {}

    /** Return list of node ids from start to end inclusive; empty if none. */
    public static List<Integer> shortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
        throw new UnsupportedOperationException("TODO");
    }
}


















































/*
ANSWER KEY:
Problem: shortest path in unweighted graph (adjacency list).
Approach: BFS with parent map; reconstruct path if end reached.
Why: BFS guarantees shortest edges count in unweighted graphs.

public static List<Integer> shortestPath(Map<Integer, List<Integer>> graph, int start, int end) {
    if (start == end) return List.of(start);
    Map<Integer,Integer> parent = new HashMap<>();
    Queue<Integer> q = new ArrayDeque<>();
    q.add(start); parent.put(start, null);
    while (!q.isEmpty()) {
        int u = q.remove();
        for (int v : graph.getOrDefault(u, List.of())) {
            if (parent.containsKey(v)) continue;
            parent.put(v, u);
            if (v == end) return build(parent, end);
            q.add(v);
        }
    }
    return List.of();
}
private static List<Integer> build(Map<Integer,Integer> parent, int end) {
    List<Integer> path = new ArrayList<>();
    Integer cur = end;
    while (cur != null) { path.add(cur); cur = parent.get(cur); }
    Collections.reverse(path);
    return path;
}
*/
