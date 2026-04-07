package gotham.asset.mgmt.GAM11;

public abstract class Solution {

    /**
     * GAM11 - The Ternary Chain
     *
     * <p>What does this return for input x = 42?</p>
     * <pre>
     *   public String classify(int x) {
     *       return x < 0 ? "negative" :
     *              x == 0 ? "zero" :
     *              x % 2 != 0 ? "odd" :
     *              x < 10 ? "small even" :
     *              x < 100 ? "medium even" :
     *              "large even";
     *   }
     * </pre>
     *
     * <p>Implement this same classification logic in a clear, readable way.</p>
     */
    public abstract String classify(int x);
}
