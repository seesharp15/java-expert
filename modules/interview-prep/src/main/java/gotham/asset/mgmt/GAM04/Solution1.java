package gotham.asset.mgmt.GAM04;

import java.util.*;

public class Solution1 extends Solution {

    /*
    *
Solution
GAM04 - HashMap Mutation During Iteration
Given a Map<String, Integer>, remove all entries where the value is negative and return the sum of remaining values.
DEBUGGING CHALLENGE: The original code throws ConcurrentModificationException. Fix it.
    public int sumPositive(Map<String, Integer> map) {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() < 0) {
                map.remove(entry.getKey());
            } else {
                sum += entry.getValue();
            }
        }
        return sum;
    }
    * */
    @Override
    public int sumPositive(Map<String, Integer> map) {
        if( map == null || map.isEmpty()) return 0;
        var entries = new ArrayList<>(map.entrySet());
        var sum = 0;

        for(var entry: entries) {
            if (entry == null) continue;

            var value = entry.getValue();
            if (value < 0) {
                map.remove(entry.getKey());
                continue;
            }

            sum += value;
        }
        return sum;
    }
}
