package gotham.asset.mgmt.GAM07.answerkey;

import gotham.asset.mgmt.GAM07.Solution;

/**
 * GAM07 Answer - The Accumulator Illusion
 *
 * The double XOR is a red herring: x ^= v; x ^= v; restores x to its original
 * value. The only operation that matters is acc += arr[i], so the method simply
 * returns the sum of the array. Answer for {3,1,4,1,5} is 14.
 */
public class Answer extends Solution {

    @Override
    public int accumulate(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int sum = 0;
        for (int value : arr) {
            sum += value;
        }
        return sum;
    }
}
