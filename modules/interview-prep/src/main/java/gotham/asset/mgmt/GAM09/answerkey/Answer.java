package gotham.asset.mgmt.GAM09.answerkey;

import gotham.asset.mgmt.GAM09.Solution;

/**
 * GAM09 Answer - Recursive Descent Confusion
 *
 * The unused variable and its recursive call are pure misdirection.
 * The method is simply computing factorial: n * (n-1) * ... * 1.
 * mystery(5) = 120.
 */
public class Answer extends Solution {

    @Override
    public int mystery(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * mystery(n - 1);
    }
}
