package leetcode.meta_prep.LC76;

import java.util.HashMap;

public class Solution3 extends Solution {
    @Override
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";


        var need = new HashMap<Character, Integer>();
        for(var c: t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        var required = need.size();
        var formed = 0;

        var window = new HashMap<Character, Integer>();

        int left = 0, right = 0, bestLength = Integer.MAX_VALUE, bestLeft = 0;

        while(right < s.length()) {
            var c = s.charAt(right++);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (window.getOrDefault(c, -1).intValue() == need.get(c).intValue()) formed++;

            while (formed == required) {
                if (right - left < bestLength) {
                    bestLength = right - left;
                    bestLeft = left;
                }

                var leftChar = s.charAt(left++);
                window.put(leftChar, window.get(leftChar) - 1);
                if (need.containsKey(leftChar) && window.get(leftChar) < need.get(leftChar)) formed --;
            }


        }

        return bestLength == Integer.MAX_VALUE ? "" : s.substring(bestLeft, bestLeft + bestLength);

    }
}
