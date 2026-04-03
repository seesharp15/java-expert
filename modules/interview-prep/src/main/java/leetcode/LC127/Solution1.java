package leetcode.LC127;

import java.sql.Array;
import java.util.*;

public class Solution1 extends Solution {

    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        var dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return 0;


        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        var steps = 1;
        while(!q.isEmpty()) {
            steps++;
            var size = q.size();
            for(var i = 0; i<size;i++) {
                var word = q.poll();
                for(var next: dict.stream().toList()) {
                    if (isAdjacent(word, next)) {
                        if (next.equals(endWord)) return steps + 1;
                        q.offer(next);
                        dict.remove(next);
                    }
                }
            }
        }
        return 0;
    }


    private static boolean isAdjacent(String a, String b){
        if (a.length() != b.length()) return false;
        var la = a.toLowerCase().toCharArray();
        var lb = b.toLowerCase().toCharArray();
        var diffs = 0;
        for(var i = 0; i<la.length;i++) {
            if (la[i] != lb[i]) {
                if (++diffs > 1) return false;
            }
        }
        return true;
    }

}
