package gotham.asset.mgmt.GAM09;

public class Solution1 extends Solution {
    /**
     * GAM09 - Recursive Descent Confusion
     *
     * <p>What does this return for n=5?</p>
     * <pre>
     *   public int mystery(int n) {
     *       if (n <= 1) return 1;
     *       int result = n;
     *       int sub = mystery(n - 1);
     *       result = result * sub;
     *       int unused = mystery(0);
     *       return result;
     *   }
     * </pre>
     *
     * <p>Implement a method that returns the same result as the above for any input n.
     * Return 1 for n <= 1.</p>
     */
    @Override
    public int mystery(int n) {
        var ans = 1;
        for(var i = 1; i <= n; i++) {
            ans *= i;
        }
        return ans;
    }
}
