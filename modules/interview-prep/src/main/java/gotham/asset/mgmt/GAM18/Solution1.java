package gotham.asset.mgmt.GAM18;

public class Solution1 extends Solution {

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
    @Override
    public int[] twoSumSorted(int[] arr, int target) {
        if (arr == null || arr.length == 0) return new int[0];
        if (arr[0] > target) return new int[0];


        int lo = 0, hi = arr.length - 1;

        while (lo < hi && arr[lo] <= target) {

            int sum = arr[lo] + arr[hi];
            if (sum == target) return new int[]{lo, hi};
            else if (sum < target) lo++;
            else hi--;
        }
        return new int[0];
    }
}
