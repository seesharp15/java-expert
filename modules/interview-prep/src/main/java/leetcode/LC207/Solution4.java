package leetcode.LC207;

import java.util.*;

public class Solution4 extends Solution {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (numCourses <= 0) return true;
        if (prerequisites == null || prerequisites.length == 0) return true;
        //build association graph & indegrees

        var graph = new HashMap<Integer, List<Integer>>();
        var indegree = new HashMap<Integer, Integer>();



        for (int i = 0; i < prerequisites.length; i++) {
            var course = prerequisites[i][0];
            var dep = prerequisites[i][1];

            graph
                    .computeIfAbsent(dep, (x) -> new ArrayList<>())
                    .add(course);

            indegree.putIfAbsent(dep, 0);
            indegree.put(course, indegree.getOrDefault(course, 0) + 1);
        }

        var q = new ArrayDeque<Integer>();
        for(var course: indegree.entrySet()) {
            if (course.getValue() == 0) q.offer(course.getKey());
        }

        if (q.isEmpty()) return false;


        var courseOrder = new ArrayList<Integer>();
        var completed = 0;
        while(!q.isEmpty()) {
            var courseId = q.poll();
            completed++;
            courseOrder.add(courseId);

            var deps = graph.get(courseId);
            if (deps == null) continue;
            for(var dep: deps) {
                indegree.put(dep, indegree.get(dep) - 1);
                if (indegree.get(dep) == 0){
                    q.offer(dep);
                }

            }
        }


        return completed == numCourses;

    }
}
