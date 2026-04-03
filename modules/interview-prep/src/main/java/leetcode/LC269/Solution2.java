package leetcode.LC269;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Solution2 extends Solution {


    @Override
    public String alienOrder(String[] words) {
        //do checks
        if (words == null || words.length == 0) return "";

        //create graph/indegrees
        var graph = new HashMap<Character, Set<Character>>(); //
        var indegrees = new HashMap<Character, Integer>();  //# neighbors

        //populate graph/indegrees
        //ensures we capture corner cases like last word if different from previous
        for(var word: words) {
            for(var c: word.toCharArray()){
                graph.putIfAbsent(c, new HashSet<>());
                indegrees.putIfAbsent(c, 0);
            }
        }

        for(var i =0; i < words.length - 1; i++) {
            var current = words[i];
            var next = words[i+1];

            if (current.length() > next.length() && current.contains(next)) return ""; //arr is out of order

            var min = Math.min(current.length(),next.length());

            for(var j = 0; j < min; j++) {
                var from = current.charAt(j);
                var to = next.charAt(j);

                if (from != to) {
                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        indegrees.put(to, indegrees.get(to) + 1);
                    }
                    break; //only first mismatch matters
                }
            }

        }


        var q = new ArrayDeque<Character>();
        for (var c: indegrees.entrySet()) {
            if (c.getValue() == 0) q.offer(c.getKey());
        }
        var result = new StringBuilder();
        while(!q.isEmpty()) {
            var current = q.pop();
            result.append(current);

            for (var dep : graph.get(current)) {
                indegrees.put(dep, indegrees.get(dep) - 1);
                if (indegrees.get(dep) == 0) {
                    q.offer(dep);
                }
            }
        }

        if (result.length() != indegrees.size()) return ""; //missing dep/cycle
        return result.toString();

        //topologically sort

        //confirm no cycle/missing dependency

        //return string
    }
}
