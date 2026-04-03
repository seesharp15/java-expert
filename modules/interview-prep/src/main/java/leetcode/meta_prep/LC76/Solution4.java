package leetcode.meta_prep.LC76;

import java.util.HashMap;
import java.util.Objects;

public class Solution4 extends Solution {
    @Override
    public String minWindow(String s, String t) {

        if (s == null || t == null ||  s.length() < t.length()) return "";

        var required = new HashMap<Character, Integer>();

        for(var c: s.toCharArray()) {
            required.put(c, required.getOrDefault(c, 0) + 1);
        }
        var requiredSize = required.size();

        var current = new HashMap<Character, Integer>();
        var fulfilled = 0;

        var right = 0;
        var left = 0;


        int[] smallest = null; //new int[0]; // {Integer.MAX_VALUE, 0};

        while(right < s.length()) {
            var val = s.charAt(right++);

            current.put(val, current.getOrDefault(val, 0) + 1);
            var count = current.get(val).intValue();

            if (required.containsKey(val) && count == required.get(val).intValue()) {
                fulfilled++;
            }


            while (fulfilled == requiredSize) {

                if (smallest == null || right - left < smallest[0]) {
                    smallest = new int[] {right - left, left};

                }
                var leftChar = s.charAt(left++);
                current.put(leftChar, current.get(leftChar) - 1);
                if (required.containsKey(leftChar) && current.get(leftChar) < required.get(leftChar)) fulfilled --;

            }
        }

        if (smallest == null)
            return "";

        return s.substring(smallest[1], smallest[0] + smallest[1]);
    }
}
