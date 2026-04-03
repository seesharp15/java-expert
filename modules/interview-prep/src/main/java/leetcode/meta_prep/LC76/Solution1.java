package leetcode.meta_prep.LC76;

import java.util.*;

/**
 * 76. Minimum Window Substring
 */
public class Solution1 extends Solution {



    @Override
    public String minWindow(String s, String t) {

        var tmap = new HashMap<Character, Integer>();
        var foundMap = new HashMap<Character, ArrayList<Integer>>();
        var foundCounts = new HashMap<Character, Integer>();

        for (var c : t.toCharArray()) {
            foundCounts.putIfAbsent(c, 0);
            foundMap.putIfAbsent(c, new ArrayList<>());

            tmap.putIfAbsent(c, 0);
            tmap.compute(c, (ch, ct) -> ct + 1);
        }


        for (var i = 0; i < s.length(); i++){
            var val = s.charAt(i);
            var mapped = tmap.get(val);
            if (mapped != null) {
                var matching = foundMap.get(val);
                matching = matching == null ? new ArrayList<>() : matching;
                matching.addLast(i);
            }
        }

        var q = new ArrayDeque<Integer>();


        var strings = new ArrayList<String>();
        //F G
        //A F F D S G H B R R E W X D F G F D S E



//        while(left < right && right < s.length()) {
//            var val = s.charAt(right);
//
//            var mapped = tmap.get(val);
//            if (mapped != null) {
//                var count = foundCounts.compute(val, (x, y) -> y + 1);
//                if (count > mapped) { //too many, drop left until
//
//                }
//            }
//        }

        var results = new ArrayList<int[]>();
        for (var right = 0; right < s.length(); right++) {
            var val = s.charAt(right);
            if (tmap.containsKey(val)) {

                var count = foundCounts.getOrDefault(val, 0);
                //foundCounts.compute(val, (x, y) -> x + 1);
                q.offer(right);
                if (count < tmap.get(val)) { //too many, drop left until
                    foundCounts.put(val, count + 1);

                    if (allFound(tmap, foundCounts)) {
                        var left = q.pop();
                        foundCounts.compute(s.charAt(left), (x, y) -> y - 1);
                        results.add(new int[]{left, right + 1});
                    }
//
                } else {
                    var cx = true;
                    while(!q.isEmpty() && cx) {
                        var tmp = q.pop();
                        cx = s.charAt(tmp) != val;
                        //foundCounts.compute(val, (x,y) -> y - 1);
                    }

                }
            }
        }

        var minLen = Integer.MAX_VALUE;
        var ans = new int[2];
        for(var result: results) {
            var len = result[1] - result[0];
            if (len < minLen) {
                ans = result;
                minLen = len;
            }
            minLen = Math.min(len, minLen);
        }
        var xz = s.substring(ans[0], ans[1]);
        return xz;

//
//            var mapped = tmap.get(val);
//            if (mapped != null) {
//                var matching = foundMap.get(val);
//
//                var count = foundCounts.compute(val, (x,y) -> y + 1);
//                if (count > mapped) { //too many, drop left until
//
//                }
//
//                if (matching.size() > mapped) {
//                    var c = q.pop();
//                    while (c != val) {
//                        c = q.pop();
//                    }
//                    matching.removeFirst();
//                }
//                foundMap.put(val, matching);
//
//                if (isValid(tmap, foundMap)) {
//
//                    strings.add()
//
//                }
//            }
//
//
//
//        }
//
//        return false;
    }

    private boolean allFound(HashMap<Character, Integer> tmap, HashMap<Character, Integer> foundCounts) {
        for(var e: tmap.entrySet()) {
            var k = e.getKey();
            var v = e.getValue();
            if (!foundCounts.get(k).equals(v)) return false;
        }
        return true;
    }

    private boolean isValid(HashMap<Character, Integer> tmap, HashMap<Character, ArrayList<Integer>> foundMap) {
        for(var e: tmap.entrySet()) {
            var f = e.getValue();
            var found = foundMap.get(e.getKey());
            if (found == null || found.size() != f) return false;
        }
        return true;
    }

}
