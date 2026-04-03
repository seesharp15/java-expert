package leetcode.LC127;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;


public class Solution4 extends Solution {

    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        var dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        var distance = 1;

        var q = new ArrayDeque<String>();
        q.offer(beginWord);

        while(!q.isEmpty()) {

            var size = q.size();
            for(var i = 0; i < size; i++) {
                var word = q.poll();
                if (word == null) break;

                var chars = word.toCharArray();

                for(var j = 0; j < chars.length; j++) {
                    var original = chars[j];

                    for(var c = 'a'; c<='z';c++) {
                        chars[j] = c;

                        var newWord = new String(chars);
                        if (newWord.equals(endWord)) return distance + 1;

                        if (dict.remove(newWord)) {
                            q.offer(newWord);
                        }
                    }
                    chars[j] = original;
                }
            }
            distance++;
        }
        return 0;
    }
}
