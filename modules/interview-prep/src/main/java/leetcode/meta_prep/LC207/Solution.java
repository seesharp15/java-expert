package leetcode.meta_prep.LC207;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * 207. Course Schedule (cycle detection via Kahn's topo)
 */
public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] pre : prerequisites) {
            graph.get(pre[1]).add(pre[0]);
            indeg[pre[0]]++;
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) if (indeg[i] == 0) q.offer(i);

        int seen = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            seen++;
            for (int nxt : graph.get(cur)) {
                if (--indeg[nxt] == 0) q.offer(nxt);
            }
        }
        return seen == numCourses;
    }
}
