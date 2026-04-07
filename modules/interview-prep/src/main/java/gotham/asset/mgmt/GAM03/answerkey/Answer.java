package gotham.asset.mgmt.GAM03.answerkey;

import gotham.asset.mgmt.GAM03.Solution;

/**
 * GAM03 Answer - The Nested Loop Trap
 *
 * For each i, the inner loop visits j in {0, 1, ..., i-1, i+1} (skipping j==i,
 * breaking when j > i+1).
 */
public class Answer extends Solution {

    @Override
    public int compute(int n) {
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i + 1; j++) {
                if (j == i) {
                    continue;
                }
                if (j >= n) {
                    break;
                }
                total += (i + j);
            }
        }
        return total;
    }
}
