package gotham.asset.mgmt.GAM13;

public abstract class Solution {

    /**
     * GAM13 - The Boolean Maze
     *
     * <p>DEBUGGING CHALLENGE: This method should return true if x is in the range [lo, hi] inclusive.
     * But it has a logic bug. Fix it.</p>
     * <pre>
     *   public boolean inRange(int x, int lo, int hi) {
     *       return !(x < lo &amp;&amp; x &gt; hi);
     *   }
     * </pre>
     */
    public abstract boolean inRange(int x, int lo, int hi);
}
