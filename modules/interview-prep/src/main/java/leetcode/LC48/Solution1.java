package leetcode.LC48;



/*
*

Code

Testcase
Testcase

Test Result
48. Rotate Image
Attempted
Medium

Topics
conpanies icon
Companies
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

*
* Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]
*
* Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
*
* Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 20
-1000 <= matrix[i][j] <= 1000

* */

import java.util.Arrays;

public class Solution1 extends Solution {
    @Override
    public void rotate(int[][] matrix) {
        var n = matrix.length;


        for(var r = 0; r < n/2;r++) {
            rotateArr(matrix, r, n);
        }

        //printMatrix(matrix); //uncomment for debug
    }

    private static void rotateArr(int[][] matrix, int row, int n) {
        //var startPosition = new int[] {row, row};

        for(var i = row; i < n-row-1; i++) {
            var first = new int[] {row, i};
            var prevValue =  matrix[first[0]][first[1]];;
            var current = first;
            for(var z = 1;z <= 4; z++) {
                current = translate(current, n);
                var secTmp = matrix[current[0]][current[1]];
                matrix[current[0]][current[1]] = prevValue;
                prevValue = secTmp;
            }

            //printMatrix(matrix); //uncomment for debug
        }
    }

    private static int[] translate(int[] origin, int size) {
        return translate(origin[0], origin[1], size);
    }

    private static int[] translate(int row, int col, int size) {
        return new int[] {col, size - 1 - row};
    }
}
