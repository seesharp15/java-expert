package leetcode.LC347;

import java.util.*;
import java.util.stream.Collectors;

public class Solution1 extends Solution {

    @Override
    public int[] topKFrequent(int[] nums, int k) {


        var map = new HashMap<Integer, Integer>();


        for(var key: nums) {
            var value = map.getOrDefault(key, 0);
            map.put(key, value + 1);
        }

        var topK = map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue()
                        .reversed())
                .limit(k)
                .map(Map.Entry::getKey).mapToInt(i -> i)
                .toArray();

        return topK;
    }
}
