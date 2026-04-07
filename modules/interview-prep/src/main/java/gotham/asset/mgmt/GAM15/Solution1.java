package gotham.asset.mgmt.GAM15;

public class Solution1 extends Solution {

    /**
     * GAM15 - The Pre/Post Increment Nightmare
     *
     * <p>What are the values of a, b, and c after this code executes?</p>
     * <pre>
     *   int a = 3;
     *   int b = a++ + --a;
     *   int c = ++a - a--;  - 3
     *                         + 3   + 3
     *                         + 4 - 4
     *                         = 9
     * </pre>
     *
     * <p>Implement a method that returns {a, b, c} for any starting value of {@code a}.
     * That is: given initialA, compute b = a++ + --a, then c = ++a - a--, return {a, b, c}.</p>
     */
    @Override
    public int[] traceIncrements(int initialA) {
        throw new RuntimeException("TODO");
    }
}
