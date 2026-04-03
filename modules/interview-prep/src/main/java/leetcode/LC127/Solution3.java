package leetcode.LC127;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

public class Solution3 extends Solution {

    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        var dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return 0;


        var q = new ArrayDeque<String>();

        q.offer(beginWord);

        var steps = 1;
        while (!q.isEmpty()) {
            var qsize = q.size();

            for (var j = 0; j < qsize; j++) {
                var word = q.poll();

                var chars = word.toCharArray();

                for (var i = 0; i < chars.length; i++) {
                    var unmod = chars[i];

                    for (var c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        var tmp = new String(chars);
                        if (tmp.equals(endWord)) return steps + 1;

                        if (dict.contains(tmp)) {
                            dict.remove(tmp);
                            q.offer(tmp);
                        }
//                        if (dict.remove(tmp)) {
//
//                            q.offer(tmp);
//                        }
                    }

                    chars[i] = unmod;
                }
            }

            steps++;
        }

        return 0;
    }
}
