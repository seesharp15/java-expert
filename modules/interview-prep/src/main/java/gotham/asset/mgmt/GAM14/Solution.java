package gotham.asset.mgmt.GAM14;

public abstract class Solution {

    /**
     * GAM14 - Matrix Diagonal Trace
     *
     * <p>What does this return for the 3x3 matrix:
     * {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}} ?</p>
     * <pre>
     *   public int diagonalSum(int[][] matrix) {
     *       int n = matrix.length;
     *       int sum = 0;
     *       for (int i = 0; i < n; i++) {
     *           sum += matrix[i][i];
     *           sum += matrix[i][n - 1 - i];
     *       }
     *       if (n % 2 == 1) {
     *           sum -= matrix[n/2][n/2];
     *       }
     *       return sum;
     *   }
     * </pre>
     *
     * <p>Implement this method so it returns the same result for any square matrix.</p>
     */
    public abstract int diagonalSum(int[][] matrix);
}
