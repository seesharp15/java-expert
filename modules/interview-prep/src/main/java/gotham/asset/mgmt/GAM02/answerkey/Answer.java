package gotham.asset.mgmt.GAM02.answerkey;

import gotham.asset.mgmt.GAM02.Solution;

/**
 * GAM02 Answer - Trace the Swap Chaos
 *
 * The two "dummy" lines are pure misdirection -- they assign to a local variable
 * that is never used again and never written back into the array. The core logic
 * is a standard two-pointer swap that reverses the array in-place.
 */
public class Answer extends Solution {

    @Override
    public void transform(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int left = 0;
        int right = a.length - 1;
        while (left < right) {
            int tmp = a[left];
            a[left] = a[right];
            a[right] = tmp;
            left++;
            right--;
        }
    }
}
