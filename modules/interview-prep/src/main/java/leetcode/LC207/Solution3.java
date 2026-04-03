package leetcode.LC207;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution3 extends Solution {
    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {


        //build a DAG


        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for(var i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }

        var indegrees = new int[numCourses];
        for(var p: prerequisites) {
            var course = p[0];
            var prereq = p[1];

            graph.get(prereq).add(course);
            indegrees[course]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(var i = 0; i < numCourses; i++) {
            if (indegrees[i] == 0) {
                q.offer(i);
            }
        }

        var completed = 0;
        while(!q.isEmpty()) {
            var current = q.poll();
            completed++;

            for(var pr: graph.get(current)){
                if (--indegrees[pr] == 0) {
                    q.offer(pr);
                }
            }
        }

        return completed == numCourses;

        //calculate indegrees



    }
}
