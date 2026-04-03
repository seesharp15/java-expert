package leetcode.LC207;

import java.util.*;

public class Solution5 extends Solution {

    //There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites
    // where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.`

    //For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
    //Return true if you can finish all courses. Otherwise, return false.
    //
    //
    //
    //Example 1:
    //
    //Input: numCourses = 2, prerequisites = [[1,0]]
    //Output: true
    //Explanation: There are a total of 2 courses to take.
    //To take course 1 you should have finished course 0. So it is possible.
    //Example 2:
    //
    //Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
    //Output: false
    //Explanation: There are a total of 2 courses to take.
    //To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
    //
    //
    //Constraints:
    //
    //1 <= numCourses <= 2000
    //0 <= prerequisites.length <= 5000
    //prerequisites[i].length == 2
    //0 <= ai, bi < numCourses
    //All the pairs prerequisites[i] are unique.
    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        if (numCourses<=0) return true;
        if (prerequisites == null || prerequisites.length == 0) return true;

        var graph = new HashMap<Integer, List<Integer>>();
        var indegree = new HashMap<Integer, Integer>();  //int[numCourses + 1];

        for(var p: prerequisites){
            var course = p[0];
            var dep = p[1];

            //if (course > numCourses || dep > numCourses) return false; //this should not happen based on the rules..
            graph.putIfAbsent(course, new ArrayList<>());
            graph.putIfAbsent(dep, new ArrayList<>());
            indegree.putIfAbsent(course, 0);
            indegree.putIfAbsent(dep, 0);


            graph.get(dep).add(course);
            indegree.compute(course, (x, y) -> y + 1);
        }

        var q = new ArrayDeque<Integer>();
        for (var id: indegree.entrySet()) {
            if (id.getValue() == 0) q.offer(id.getKey());
        }


        var completed = 0;
        while(!q.isEmpty()) {
            completed++;
            var course = q.poll();

            for(var dep: graph.get(course)) {
                if (indegree.compute(dep, (x, y) -> y - 1) == 0){
                    q.offer(dep);
                }
            }
        }

        return completed == numCourses;
    }
}
