package leetcode.LC62;

/**
 * 62. Unique Paths
 * A robot is located at the top-left corner of an m x n grid (0,0)
 * and can move only right or down. Return the number of unique paths
 * to reach the bottom-right corner (m-1, n-1).
 */
public class Solution {

    // Combinatorial DP in O(min(m,n)) space.
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;
        // ensure n <= m for smaller buffer
        if (n > m) {
            int tmp = m; m = n; n = tmp;
        }
        int[] dp = new int[n];
        for (int j = 0; j < n; j++) dp[j] = 1; // first row

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1]; // current = top (dp[j]) + left (dp[j-1])
            }
        }
        return dp[n - 1];
    }
}
