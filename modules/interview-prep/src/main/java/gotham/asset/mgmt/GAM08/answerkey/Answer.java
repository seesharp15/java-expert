package gotham.asset.mgmt.GAM08.answerkey;

import gotham.asset.mgmt.GAM08.Solution;

/**
 * GAM08 Answer - The Modulo Trap
 *
 * Bug 1: Loop should run from i=1 to i<=n (inclusive), not i=0 to i<n.
 * Bug 2: The divisibility check for 15 must come BEFORE checks for 3 and 5.
 */
public class Answer extends Solution {

    @Override
    public String fizzBuzz(int n) {
        if (n <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            if (i > 1) {
                sb.append(",");
            }
            if (i % 15 == 0) {
                sb.append("FizzBuzz");
            } else if (i % 3 == 0) {
                sb.append("Fizz");
            } else if (i % 5 == 0) {
                sb.append("Buzz");
            } else {
                sb.append(i);
            }
        }
        return sb.toString();
    }
}
