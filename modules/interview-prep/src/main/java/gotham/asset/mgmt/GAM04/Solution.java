package gotham.asset.mgmt.GAM04;

import java.util.Map;

public abstract class Solution {

    /**
     * GAM04 - HashMap Mutation During Iteration
     *
     * <p>Given a Map<String, Integer&gt;, remove all entries where the value is negative
     * and return the sum of remaining values.</p>
     *
     * <p>DEBUGGING CHALLENGE: The original code throws ConcurrentModificationException. Fix it.</p>
     * <pre>
     *   public int sumPositive(Map<String, Integer&gt; map) {
     *       int sum = 0;
     *       for (Map.Entry<String, Integer&gt; entry : map.entrySet()) {
     *           if (entry.getValue() < 0) {
     *               map.remove(entry.getKey());
     *           } else {
     *               sum += entry.getValue();
     *           }
     *       }
     *       return sum;
     *   }
     * </pre>
     */
    public abstract int sumPositive(Map<String, Integer> map);
}
