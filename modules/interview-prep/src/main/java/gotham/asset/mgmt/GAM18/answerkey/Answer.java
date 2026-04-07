package gotham.asset.mgmt.GAM18.answerkey;

import gotham.asset.mgmt.GAM18.Solution;

/**
 * GAM18 Answer - Two Pointer Misfire
 *
 * Two bugs fixed:
 *   1. hi initialized to arr.length - 1 (not arr.length)
 *   2. Loop condition changed to lo < hi (not lo <= hi)
 */
public class Answer extends Solution {

    @Override
    public int[] twoSumSorted(int[] arr, int target) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int sum = arr[lo] + arr[hi];
            if (sum == target) {
                return new int[]{lo, hi};
            } else if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return new int[0];
    }
}
