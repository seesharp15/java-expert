package leetcode.LC269;

import java.util.*;

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
public class Solution {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // Step 1: add every character so isolated chars are included
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // Step 2: build edges from adjacent words
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];

            // Invalid case: longer word comes before its own prefix
            if (first.length() > second.length() && first.startsWith(second)) {
                return "";
            }

            int minLen = Math.min(first.length(), second.length());
            for (int j = 0; j < minLen; j++) {

              char from = first.charAt(j);
                char to = second.charAt(j);

                if (from != to) {
                    // Only add edge once
                    if (!graph.get(from).contains(to)) {
                        graph.get(from).add(to);
                        indegree.put(to, indegree.get(to) + 1);
                    }
                    break; // only first difference matters
                }
            }
        }

        // Step 3: topological sort
        Queue<Character> queue = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            char current = queue.poll();
            result.append(current);

            for (char neighbor : graph.get(current)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If not all chars were processed, there is a cycle
        if (result.length() != indegree.size()) {
            return "";
        }

        return result.toString();
    }
}
