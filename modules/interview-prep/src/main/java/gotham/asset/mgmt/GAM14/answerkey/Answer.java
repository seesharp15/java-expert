package gotham.asset.mgmt.GAM14.answerkey;

import gotham.asset.mgmt.GAM14.Solution;

/**
 * GAM14 Answer - Matrix Diagonal Trace
 *
 * Sums the main diagonal and anti-diagonal of a square matrix. When the matrix
 * has odd dimensions, the center element is subtracted once to correct for double-counting.
 */
public class Answer extends Solution {

    @Override
    public int diagonalSum(int[][] matrix) {
        int n = matrix.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += matrix[i][i];
            sum += matrix[i][n - 1 - i];
        }
        if (n % 2 == 1) {
            sum -= matrix[n / 2][n / 2];
        }
        return sum;
    }
}
