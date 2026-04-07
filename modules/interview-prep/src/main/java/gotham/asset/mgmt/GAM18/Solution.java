package gotham.asset.mgmt.GAM18;

public abstract class Solution {

    /**
     * GAM18 - Two Pointer Misfire
     *
     * <p>DEBUGGING CHALLENGE: In a SORTED array, find two elements that sum to target.
     * Return their indices as [i, j] where i < j. Return empty array if not found.</p>
     * <pre>
     *   public int[] twoSumSorted(int[] arr, int target) {
     *       int lo = 0, hi = arr.length;
     *       while (lo <= hi) {
     *           int sum = arr[lo] + arr[hi];
     *           if (sum == target) return new int[]{lo, hi};
     *           else if (sum < target) lo++;
     *           else hi--;
     *       }
     *       return new int[0];
     *   }
     * </pre>
     *
     * <p>Find and fix the bugs.</p>
     */
    public abstract int[] twoSumSorted(int[] arr, int target);
}
