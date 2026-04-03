package leetcode.LC127;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;

/*
* 127. Word Ladder

A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest
* transformation sequence from beginWord to endWord, or 0 if no such sequence exists.



Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: 0
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


Constraints:

1 <= beginWord.length <= 10
endWord.length == beginWord.length
1 <= wordList.length <= 5000
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
* */
public class Solution7 extends Solution {

    @Override
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) return 0;

        var dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;
        if (beginWord.equals(endWord)) return 1;

        var q = new ArrayDeque<String>();
        q.offer(beginWord);
        var steps = 1;
        while(!q.isEmpty()) {
            var size = q.size();

            for(var i = 0; i < size; i++) {
                var word = q.poll();
                var chars = word.toCharArray();

                for(var c = 0; c < chars.length; c++) {
                    var original = chars[c];

                    for(var ch = 'a'; ch <= 'z'; ch++) {
                        chars[c] = ch;
                        var newWord = new String(chars);
                        if (newWord.equals(endWord)) return steps + 1;

                        if (dict.remove(newWord)) {
                            q.offer(newWord);
                        }
                    }
                    chars[c] = original;
                }
            }
            steps++;
        }

        return 0;

    }
}
