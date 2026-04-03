package leetcode.meta_prep.LC76;


/*
*
*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such
* that every character in t (including duplicates) is included in the window.
* If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.


Example 1:

* //ADOBEC
* //BECODEBA
* //CODEBA
* //BANC
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

import java.util.HashMap;

public class Solution7 extends Solution {
    @Override
    public String minWindow(String s, String t) {
        if (s == null || t == null || t.isEmpty() || s.length() < t.length()) return "";

        var required = new HashMap<Character, Integer>();
        var current = new HashMap<Character, Integer>();

        for(var c: t.toCharArray()){
            required.put(c, required.getOrDefault(c, 0) + 1);
        }

        //var requirements = required.size();
        var matches = 0;
        var left = 0;
        var right = 0;

        var smallest = new int[] { Integer.MAX_VALUE, Integer.MAX_VALUE };

        while(right < s.length()) {
            var c = s.charAt(right++);
            current.put(c, current.getOrDefault(c, 0) + 1);

            if (required.containsKey(c) && current.get(c).intValue() == required.get(c)) {
                matches++;
            }

            while (matches == required.size()) {
                //completed a string
                var size = right - left;
                if (size < smallest[1]) {
                    smallest[0] = left;
                    smallest[1] = size;
                }

                var leftChar = s.charAt(left++);
                current.put(leftChar, current.get(leftChar) - 1);

                if (required.containsKey(leftChar) && current.get(leftChar).intValue() < required.get(leftChar)) {
                    matches--;
                    break;
                }
            }
        }

        if (smallest[1] == Integer.MAX_VALUE) return "";
        return s.substring(smallest[0], smallest[1] + smallest[0]);
    }
}

//* //ADOBEC
//* //BECODEBA
//* //CODEBA
//* //BANC