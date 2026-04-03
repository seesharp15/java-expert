package leetcode.meta_prep.LC76;

import java.util.HashMap;

/*
*
*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such
* that every character in t (including duplicates) is included in the window.
* If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.


Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.
Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.


Follow up: Could you find an algorithm that runs in O(m + n) time?
* */
public class Solution6 extends Solution {

    @Override
    public String minWindow(String s, String t) {
        if (s == null || t == null || t.isEmpty() || s.length() < t.length()) return "";

        var freqMap = new HashMap<Character, Integer>();
        for(var c: t.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int left = 0,
            right = 0,
            bestLength = Integer.MAX_VALUE,
            bestStart = 0,
            criteria = 0;

        var current = new HashMap<Character, Integer>();
        while(right < s.length()) {
            var c = s.charAt(right++);
            current.put(c, current.getOrDefault(c, 0) + 1);

            if (freqMap.containsKey(c) && current.get(c).intValue() == freqMap.get(c).intValue()) {
                criteria++;
            }

            while (criteria == freqMap.size()) {
                //we have a completion
                var length = right - left;
                if (length < bestLength) {
                    bestLength = length;
                    bestStart = left;
                }

                var leftChar = s.charAt(left++);
                current.put(leftChar, current.get(leftChar) - 1);

                if (freqMap.containsKey(leftChar) && current.get(leftChar) < freqMap.get(leftChar)) {
                    criteria--;
                    break;
                }
            }
        }

        return bestLength == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestLength + bestStart);
    }
}
