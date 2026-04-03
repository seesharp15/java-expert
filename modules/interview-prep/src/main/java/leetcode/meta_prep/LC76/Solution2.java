package leetcode.meta_prep.LC76;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Solution2 extends Solution {

    private class CharInfo {
        int found, required = 0;
    }

    @Override
    public String minWindow(String s, String t) {


        var chars = new HashMap<Character, CharInfo>();
        if (s.length() < t.length()) return "";
        if (t.isEmpty()) return "";


        for(var c: t.toCharArray()) {
            chars.putIfAbsent(c, new CharInfo());
            chars.get(c).required++;
        }

        var currentDistance = Integer.MAX_VALUE;
        var shortest = new int[0];


        var left = 0;
        var right = 0;
        var q = new ArrayDeque<Integer>();
        q.offer(0);
        while(left <= right && !q.isEmpty()) {
            var c = s.charAt(right);
            if (chars.containsKey(c)) {
                q.offer(right);

                var charInfo = chars.get(c);
                charInfo.found++;

            }

            right += 1;
            if (right > s.length() - 1) {
                right = s.length() - 1;
                left = q.pop();
                if (q.isEmpty()) break;
            }


            var isMatch = true;
            for(var ch: chars.values()) {
                if (ch.found < ch.required) {
                    isMatch = false;
                    break;
                }
            }
            if (!isMatch) {

                continue;
            }

            var leftChar = s.charAt(left);
            chars.get(leftChar).found--;

            var distance = right - left;
            if (distance < currentDistance) {
                currentDistance = distance;
                shortest = new int[] {left, right};
            }


            left = q.pop();

//            if (chars.containsKey(c)) {
//                    q.offer(right);
//
//
//
//                    if (isMatch) {
//                        left = q.pop();
//                        var leftChar = s.charAt(left);
//                        chars.get(leftChar).found--;
//
//                        var distance = right - left;
//                        if (distance < currentDistance) {
//                            currentDistance = distance;
//                            shortest = new int[] {left, right};
//                        }
//
//                    }
//
//            }
//            right++;
//            if (right >= s.length()) {
//                right = s.length() - 1;
//                left ++;
//            }
        }

        if (shortest.length == 0) return "";
        var ans = s.substring(shortest[0], shortest[1]+1);
        return ans;

    }
}
