package leetcode.LC207;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution1 extends Solution {

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
        //find all classes without a pre-req

        //build a full DAG graph of courses with all pre-reqs

        List<List<Integer>> graph = new ArrayList<>();
        for(var i = 0;i < numCourses;i++) {
            graph.add(new ArrayList<>());
        }

        var indegree = new int[numCourses];

        for(var pre: prerequisites) {
            var course = pre[0];
            var prereq = pre[1];

            graph.get(prereq).add(course);
            indegree[course]++;
        }


        Queue<Integer> q = new ArrayDeque<>();

        for(var i = 0;i<numCourses;i++) {
            if (indegree[i] == 0){
                q.offer(i);
            }
        }

        var completed = 0;
        while(!q.isEmpty()) {
            var current = q.poll();

            completed++;
            for(var next : graph.get(current)) {
                indegree[next]--;
                if (indegree[next] == 0){
                    q.offer(next);
                }
            }
        }

        return completed == numCourses;
    }
}
