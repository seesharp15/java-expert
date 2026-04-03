package leetcode.LC210;

import java.util.*;

public class Solution1 extends Solution {

    @Override
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        var graph = new HashMap<Integer, List<Integer>>();
        var indegree = new HashMap<Integer, Integer>();

        if (prerequisites == null || prerequisites.length == 0) return new int[] {0};

        for(var p: prerequisites) {

            var course = p[0];
            var dep = p[1];

            graph.putIfAbsent(course, new ArrayList<>());

            graph.computeIfAbsent(dep, x -> new ArrayList<>())
                    .add(course);

            indegree.putIfAbsent(dep, 0);
            indegree.putIfAbsent(course, 0);

            indegree.compute(course, (x,y) -> y + 1);

        }

        var q = new ArrayDeque<Integer>();
        for(var p: indegree.entrySet()) {
            if (p.getValue() == 0) q.offer(p.getKey());
        }


        var courseOrder = new HashSet<Integer>();
        var completed = 0;

        while(!q.isEmpty()) {
            completed++;
            var courseId = q.poll();
            courseOrder.add(courseId);

            for(var dep: graph.get(courseId)) {
                var degree = indegree.compute(dep, (x, y) -> y - 1);

                if (degree == 0){
                    q.offer(dep);
                }
            }
        }

        if (completed != numCourses) {
            return new int[0];
        }

        var result = new int[courseOrder.size()];
        var indx = 0;
        for(var course: courseOrder) {
            result[indx] = course;
            indx ++;
        }

        return result;

    }
}
