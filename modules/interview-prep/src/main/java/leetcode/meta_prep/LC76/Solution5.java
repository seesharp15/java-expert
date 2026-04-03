package leetcode.meta_prep.LC76;

import java.util.HashMap;

public class Solution5 extends Solution {


    @Override
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length())
            return "";

        var required = new HashMap<Character, Integer>();
        var current = new HashMap<Character, Integer>();

        for(var c: t.toCharArray()) {
            required.compute(c, (k, total) -> total == null ? 1 : total + 1);
        }

        var requiredCharCount = required.size();
        int completed = 0, left = 0, right = 0, bestLength = Integer.MAX_VALUE, bestLeft = 0;

        while(right < s.length() ) {
            var val = s.charAt(right++);
            var foundCount = current.compute(val, (k, total) -> total == null ? 1 : total + 1);
            if (required.containsKey(val) && foundCount == required.get(val).intValue()) {
                    completed++;
            }

            while (completed == requiredCharCount) {
                var length = right - left;
                if (length < bestLength) {
                    bestLeft = left;
                    bestLength = length;
                }
                var lc = s.charAt(left++);
                current.compute(lc, (c,v) -> v - 1);;
                if (required.containsKey(lc) && current.get(lc) < required.get(lc)) {
                    completed -= 1;
                }
            }
        }

        if (bestLength == Integer.MAX_VALUE) return "";
        return s.substring(bestLeft, bestLeft + bestLength);
    }
}