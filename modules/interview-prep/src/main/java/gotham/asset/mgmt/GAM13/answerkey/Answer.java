package gotham.asset.mgmt.GAM13.answerkey;

import gotham.asset.mgmt.GAM13.Solution;

/**
 * GAM13 Answer - The Boolean Maze
 *
 * Bug: The original uses !(x < lo && x > hi), but by De Morgan's law this becomes
 * (x >= lo || x <= hi), which is always true. Fix: use x >= lo && x <= hi.
 */
public class Answer extends Solution {

    @Override
    public boolean inRange(int x, int lo, int hi) {
        return x >= lo && x <= hi;
    }
}
