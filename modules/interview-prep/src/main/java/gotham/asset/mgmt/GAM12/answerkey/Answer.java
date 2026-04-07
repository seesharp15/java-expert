package gotham.asset.mgmt.GAM12.answerkey;

import gotham.asset.mgmt.GAM12.Solution;

/**
 * GAM12 Answer - The Index Dance
 *
 * Follow the index chain: start at index 0, repeatedly jump to arr[idx].
 * Stop when idx reaches the last position or after arr.length steps (to avoid
 * infinite loops from cycles).
 */
public class Answer extends Solution {

    @Override
    public int dance(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int idx = 0;
        int steps = 0;
        while (idx != arr.length - 1 && steps < arr.length) {
            idx = arr[idx];
            steps++;
        }
        return idx;
    }
}
