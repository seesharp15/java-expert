package leetcode.LC48;

public class Solution {
    public void rotate(int[][] matrix) {
        throw new RuntimeException("TODO");
    }


    protected static void printMatrix(int[][] matrix) {
        int maxWidth = 0;

        // Step 1: find max width
        for (int[] row : matrix) {
            for (int val : row) {
                int len = String.valueOf(val).length();
                if (len > maxWidth) {
                    maxWidth = len;
                }
            }
        }

        // Step 2: print with padding
        for (int[] row : matrix) {
            System.out.print("[");
            for (int col = 0; col < row.length; col++) {
                String formatted = String.format("%" + maxWidth + "d", row[col]);
                System.out.print(formatted);

                if (col < row.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
        System.out.println();

    }

}
