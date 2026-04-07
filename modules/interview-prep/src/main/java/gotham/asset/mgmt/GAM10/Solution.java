package gotham.asset.mgmt.GAM10;

public abstract class Solution {

    /**
     * GAM10 - Array Rotation Bug
     *
     * <p>Rotate an array LEFT by k positions.</p>
     * <p>Example: [1, 2, 3, 4, 5], k=2 -&gt; [3, 4, 5, 1, 2]</p>
     *
     * <p>DEBUGGING CHALLENGE: This code has edge-case bugs. Fix them.</p>
     * <pre>
     *   public int[] rotateLeft(int[] arr, int k) {
     *       int n = arr.length;
     *       int[] result = new int[n];
     *       for (int i = 0; i < n; i++) {
     *           result[i] = arr[(i + k) % n];
     *       }
     *       return result;
     *   }
     * </pre>
     *
     * <p>Consider all possible inputs: what values of arr and k could cause problems?</p>
     */
    public abstract int[] rotateLeft(int[] arr, int k);
}
