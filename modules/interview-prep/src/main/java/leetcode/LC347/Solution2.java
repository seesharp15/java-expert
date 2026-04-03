package leetcode.LC347;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution2 extends Solution {
    @Override
    public int[] topKFrequent(int[] nums, int k) {

        var map = new HashMap<Integer, Integer>();

        for(var num: nums){
            map.putIfAbsent(num, 0);
            map.compute(num, (key,value) -> value + 1);
        }


        return map.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue()
                        .reversed()
                        .thenComparing(Map.Entry.comparingByKey())
                ).limit(k)
                .map(Map.Entry::getKey)
                .mapToInt(i -> i).toArray();

    }
}
