package leetcode.LC127;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

public class Solution2 extends Solution {

    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        var dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        var steps = 1;
        var q = new ArrayDeque<String>();

        q.offer(beginWord);

        while(!q.isEmpty()) {

            var qsize = q.size();


            for(var i = 0; i < qsize; i++) {
                var word = q.poll();
                var wa = word.toCharArray();

                for(var ci = 0; ci < wa.length; ci++) {
                    var orig = wa[ci];

                    for(var oc = 'a'; oc <= 'z'; oc++) {
                        wa[ci] = oc;
                        var next = new String(wa);
                        if (next.equals(endWord)) return steps + 1;

                        if (dict.contains(next)) {
                            q.offer(next);
                            dict.remove(next);
                        }
                    }
                    wa[ci] = orig;
                }
            }
            steps++;
        }

        return 0;
    }
}
