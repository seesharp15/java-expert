package leetcode.LC207;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Solution2 extends Solution {

    @Override
    public boolean canFinish(int numCourses, int[][] prerequisites) {


        //build a DAG -- not acyclic because course/prereqs are unique/
        //ensure that number of fully completable courses is the same as the number of courses

        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for(var i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());

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

        // [1, 0]
        // [2, 1]
        // [3, 1]
        // [3, 2]
        // [4, 3]


        //c[1] = 0
        //c[2] = 1
        //c[3] = 2
        //c[4] = 1

        //comp = 1

        var completed = 0;
        while(!q.isEmpty()) {
            var current = q.poll();
            completed++;

            for(var p: graph.get(current)) {
                if (--indegrees[p] == 0){
                    q.offer(p);
                }
            }
        }

        return completed == numCourses;



        //kahn:
            //get indegrees
        //process each until indegrees = 0;


    }
}
