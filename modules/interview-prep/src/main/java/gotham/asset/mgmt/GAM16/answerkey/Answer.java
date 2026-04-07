package gotham.asset.mgmt.GAM16.answerkey;

import gotham.asset.mgmt.GAM16.Solution;

/**
 * GAM16 Answer - Exception Flow Labyrinth
 *
 * Inner catch doesn't match RuntimeException (only NullPointerException).
 * Inner finally appends "E", exception propagates to outer catch ("G"),
 * then outer finally ("H"). Result: "ABEGH".
 */
public class Answer extends Solution {

    @Override
    public String exceptionMaze() {
        return "ABEGH";
    }
}
