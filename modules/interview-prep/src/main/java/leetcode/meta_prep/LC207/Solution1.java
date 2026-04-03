package leetcode.meta_prep.LC207;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 207. Course Schedule


 Hint
 There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you
 must take course bi first if you want to take course ai.

 For example, the pair [0, 1], indicates that to take course 0 you have to first take
 course 1. Return true if you can finish all courses. Otherwise, return false.



 Example 1:

 Input: numCourses = 2, prerequisites = [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.
 Example 2:

 Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0
 you should also have finished course 1. So it is impossible.


 */

public class Solution1 extends Solution {
    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        var graph = new HashMap<Integer, HashSet<Integer>>();
        var indegree = new int[numCourses];

        for(var i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }

        for(var p: prerequisites) {
            var course = p[0];
            var prereq = p[1];

            graph.get(prereq).add(course);
            indegree[course]++;
        }

        var q = new ArrayDeque<Integer>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        var completed = 0;
        while(!q.isEmpty()) {
            completed++;
            var course = q.poll();

            for(var p: graph.get(course)) {
                if (--indegree[p] == 0) {
                    q.offer(p);
                }
            }
        }

        return completed == numCourses;
    }
}
