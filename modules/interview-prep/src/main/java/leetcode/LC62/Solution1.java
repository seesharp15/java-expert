package leetcode.LC62;

import java.util.Arrays;

/**
 * 62. Unique Paths
 * A robot starts at (0,0) in an m x n grid, moving only right or down.
 * Return the number of unique paths to (m-1, n-1).
 */
public class Solution1 extends Solution {

    @Override
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return 0;

        var dp = new int[n];
        Arrays.fill(dp, 1);
        for(var i = 1; i < m; i++) {
            for (var j = 1; j < n; j++){
                dp[j] += dp[j-1];
            }
        }

        return dp[n-1];
    }
}
