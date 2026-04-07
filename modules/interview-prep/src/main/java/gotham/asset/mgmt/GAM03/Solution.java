package gotham.asset.mgmt.GAM03;

public abstract class Solution {

    /**
     * GAM03 - The Nested Loop Trap
     *
     * <p>What value does this method return for input n=5?
     * Trace through carefully - the break and continue make it tricky.</p>
     * <pre>
     *   public int compute(int n) {
     *       int total = 0;
     *       for (int i = 0; i < n; i++) {
     *           for (int j = 0; j < n; j++) {
     *               if (j == i) continue;
     *               if (j &gt; i + 1) break;
     *               total += (i + j);
     *           }
     *       }
     *       return total;
     *   }
     * </pre>
     *
     * <p>Implement a method that produces the SAME result as the above
     * but in a clear, readable way. Your implementation must return the same
     * value for any non-negative n.</p>
     */
    public abstract int compute(int n);
}
