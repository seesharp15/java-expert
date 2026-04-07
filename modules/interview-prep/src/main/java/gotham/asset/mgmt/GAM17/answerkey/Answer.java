package gotham.asset.mgmt.GAM17.answerkey;

import gotham.asset.mgmt.GAM17.Solution;

/**
 * GAM17 Answer - The Sorting Mirage
 *
 * This performs a single pass of bubble sort. After one pass, the largest element
 * is guaranteed to be at the end, but the rest may still be unsorted.
 */
public class Answer extends Solution {

    @Override
    public void process(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
    }
}
