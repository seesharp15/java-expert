package leetcode.LC269;

/*
* Prompt
You are given a list of words from an alien language. The words are already sorted according to that language’s dictionary order.
Your job is to determine a valid ordering of the alien alphabet.
Rules:
If word A comes before word B in sorted order, then the first differing character between them tells you that one letter must come before the other.
Return a string representing one valid letter ordering.
If there is no valid ordering because the constraints conflict, return "".
If a word is a prefix of an earlier longer word, that is invalid. Example: ["abc","ab"] should return "".
Example:
Input: ["wrt","wrf","er","ett","rftt"]
Output: "wertf"
* */

import java.util.*;

public class Solution1 extends Solution {

    class Letter {
        char value;
        Letter prev,next;
        Letter(char v) { value = v; }
        Letter(){}
    }

    @Override
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        var graph = new HashMap<Character, Set<Character>>();
        var indegrees = new HashMap<Character, Integer>();

        for(var word:words) {
            for(var c: word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegrees.putIfAbsent(c, 0);
            }
        }

        for(var i = 0; i < words.length-1; i++){
            var word = words[i];
            var nextWord = words[i+1];

            if (word.length() > nextWord.length() && word.contains(nextWord)) return "";

            var min = Math.min(word.length(), nextWord.length());

            for(var j = 0; j < min; j++){
                var from = word.charAt(j);
                var to = nextWord.charAt(j);

                if (from != to) {
                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        indegrees.put(to, indegrees.get(to) + 1);
                    }
                    break;
                }
            }
        }

        //sort topologically

        var q = new ArrayDeque<Character>();
        for(var c: indegrees.entrySet()) {
            if (c.getValue() == 0) q.offer(c.getKey());
        }
        var result = new StringBuilder();
        while(!q.isEmpty()) {
            var c = q.pop();
            result.append(c);
            for(var neighbor: graph.get(c)) {
                indegrees.put(neighbor, indegrees.get(neighbor) - 1);
                if (indegrees.get(neighbor) == 0){
                    q.offer(neighbor);
                }
            }
        }

        if (result.length() != indegrees.size()) {
            return "";
        }

        return result.toString();


    }

}
