package gotham.asset.mgmt.GAM11;

public class Solution1 extends Solution {
    /**
     * GAM11 - The Ternary Chain
     *
     * <p>What does this return for input x = 42?</p>
     * <pre>
     *   public String classify(int x) {
     *       return x < 0 ?
     *                  "negative" :
     *                  x == 0 ? "zero" :
     *                      x % 2 != 0 ? "odd" :
     *                      x < 10 ? "small even" :
     *                          x < 100 ? "medium even" :
     *                             "large even";
     *   }
     * </pre>
     *
     * <p>Implement this same classification logic in a clear, readable way.</p>
     */

    //42 returns "medium even";
    @Override
    public String classify(int x) {
        if (x < 0) return "negative";
        else if (x == 0) return "zero";
        else if (!(x % 2 == 0)) return "odd";
        else if (x < 10) return "small even";
        else if (x < 100) return "medium even";
        else return "large even";
    }
}
