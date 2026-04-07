package gotham.asset.mgmt.GAM02;

public abstract class Solution {

    /**
     * GAM02 - Trace the Swap Chaos
     *
     * <p>What is the final state of array {@code a} after this method executes?</p>
     * <pre>
     *   public void transform(int[] a) {
     *       int p = 0, q = a.length - 1;
     *       while (p < q) {
     *           int tmp = a[p];
     *           a[p] = a[q];
     *           a[q] = tmp;
     *           int dummy = a[p];
     *           dummy = dummy ^ a[q];
     *           p++;
     *           q--;
     *       }
     *   }
     * </pre>
     *
     * <p>Implement a clean version that produces the same result as the above.</p>
     */
    public abstract void transform(int[] a);
}
