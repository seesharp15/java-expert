package gotham.asset.mgmt.GAM06;

public abstract class Solution {

    /**
     * GAM06 - Bit Manipulation Maze
     *
     * <p>What does this method return for input x=12, y=10?</p>
     * <pre>
     *   public int bitMaze(int x, int y) {
     *       int a = x ^ y;
     *       int b = (x &amp; y) << 1;
     *       while (b != 0) {
     *           int temp = a ^ b;
     *           b = (a &amp; b) << 1;
     *           a = temp;
     *       }
     *       return a;
     *   }
     * </pre>
     *
     * <p>Implement a method that returns the same result for any two non-negative integers.</p>
     */
    public abstract int bitMaze(int x, int y);
}
