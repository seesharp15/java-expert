package gotham.asset.mgmt.GAM11.answerkey;

import gotham.asset.mgmt.GAM11.Solution;

/**
 * GAM11 Answer - The Ternary Chain
 *
 * The nested ternary is equivalent to a straightforward if/else chain.
 * For x = 42 the answer is "medium even" (positive, even, 10 <= 42 < 100).
 */
public class Answer extends Solution {

    @Override
    public String classify(int x) {
        if (x < 0) {
            return "negative";
        } else if (x == 0) {
            return "zero";
        } else if (x % 2 != 0) {
            return "odd";
        } else if (x < 10) {
            return "small even";
        } else if (x < 100) {
            return "medium even";
        } else {
            return "large even";
        }
    }
}
