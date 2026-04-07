package gotham.asset.mgmt.GAM06.answerkey;

import gotham.asset.mgmt.GAM06.Solution;

/**
 * GAM06 Answer - Bit Manipulation Maze
 *
 * This is the classic bit-manipulation addition algorithm. It computes x + y
 * without using the + operator.
 *
 * For x=12, y=10 the answer is 22 (i.e., 12 + 10).
 */
public class Answer extends Solution {

    @Override
    public int bitMaze(int x, int y) {
        return x + y;
    }
}
