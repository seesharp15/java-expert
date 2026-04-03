package leetcode.LC887;

/**
 * 887. Super Egg Drop
 * Given k eggs and a building with n floors, return the minimum number of moves
 * needed to determine the highest safe floor.
 */
public class Solution {
    /**
     * Efficient DP using move-count expansion:
     * dp[m][e] = maximum floors that can be tested with m moves and e eggs.
     * We iterate moves until coverage >= n, using 1D space rolling from high to low eggs.
     */
    public int superEggDrop(int k, int n) {
        if (n <= 0) return 0;
        if (k <= 0) throw new IllegalArgumentException("Egg count must be positive");

        long[] dp = new long[k + 1];
        int moves = 0;

        while (dp[k] < n) {
            moves++;
            for (int eggs = k; eggs >= 1; eggs--) {
                dp[eggs] = dp[eggs] + dp[eggs - 1] + 1;
            }
        }

        return moves;
    }
}
