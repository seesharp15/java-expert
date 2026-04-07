package gotham.asset.mgmt.GAM12;

public abstract class Solution {

    /**
     * GAM12 - The Index Dance
     *
     * <p>What is the final value of {@code idx} after this code runs with arr = {2, 3, 1, 0, 4}?</p>
     * <pre>
     *   public int dance(int[] arr) {
     *       int idx = 0;
     *       int steps = 0;
     *       while (idx != arr.length - 1 &amp;&amp; steps < arr.length) {
     *           idx = arr[idx];
     *           steps++;
     *       }
     *       return idx;
     *   }
     * </pre>
     *
     * <p>Implement this method. Return -1 for null or empty input.</p>
     */
    public abstract int dance(int[] arr);
}
