package leetcode.meta_prep.LC76;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring
 */
public class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);

        int required = need.size();
        int formed = 0;
        Map<Character, Integer> window = new HashMap<>();
        int l = 0, r = 0;
        int bestLen = Integer.MAX_VALUE, bestL = 0;

        while (r < s.length()) {
            char c = s.charAt(r++);
            window.put(c, window.getOrDefault(c, 0) + 1);
            if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) formed++;

            while (formed == required) {
                if (r - l < bestLen) { bestLen = r - l; bestL = l; }
                char lc = s.charAt(l++);
                window.put(lc, window.get(lc) - 1);
                if (need.containsKey(lc) && window.get(lc) < need.get(lc)) formed--;
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestL, bestL + bestLen);
    }
}
