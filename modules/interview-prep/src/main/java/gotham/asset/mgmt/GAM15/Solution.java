package gotham.asset.mgmt.GAM15;

public abstract class Solution {

    /**
     * GAM15 - The Pre/Post Increment Nightmare
     *
     * <p>What are the values of a, b, and c after this code executes?</p>
     * <pre>
     *   int a = 3;
     *   int b = a++ + --a;
     *   int c = ++a - a--;
     * </pre>
     *
     * <p>Implement a method that returns {a, b, c} for any starting value of {@code a}.
     * That is: given initialA, compute b = a++ + --a, then c = ++a - a--, return {a, b, c}.</p>
     */
    public abstract int[] traceIncrements(int initialA);
}
