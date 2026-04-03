package leetcode.LC48;

public class Solution2 extends Solution {
    @Override
    public void rotate(int[][] matrix) {
        var n = matrix.length;

        for(var r = 0; r < n/2;r++) {
            for(var i = r; i < n-r-1; i++) {
                var row = r;
                var col = i;
                var prev = matrix[row][col];

                for(var z = 0;z < 4; z++) {
                    var nextRow = col;
                    var nextCol = n - 1 - row;
                    var tmp = matrix[nextRow][nextCol];
                    matrix[nextRow][nextCol] = prev;
                    prev = tmp;

                    row = nextRow;
                    col = nextCol;
                }
            }
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
