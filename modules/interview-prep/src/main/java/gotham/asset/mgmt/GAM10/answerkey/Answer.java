package gotham.asset.mgmt.GAM10.answerkey;

import gotham.asset.mgmt.GAM10.Solution;

/**
 * GAM10 Answer - Array Rotation Bug
 *
 * Fixes: Handle null/empty arrays. Normalize k with ((k % n) + n) % n to handle
 * negative values of k.
 */
public class Answer extends Solution {

    @Override
    public int[] rotateLeft(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int normalizedK = ((k % n) + n) % n;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = arr[(i + normalizedK) % n];
        }
        return result;
    }
}
