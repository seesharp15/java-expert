package gotham.asset.mgmt.GAM05.answerkey;

import gotham.asset.mgmt.GAM05.Solution;

/**
 * GAM05 Answer - String Builder Deception
 *
 * The loop manually reverses the string by swapping chars from outside in.
 * Then sb.reverse() reverses it back. The two operations cancel out,
 * so the method returns the original input string unchanged.
 */
public class Answer extends Solution {

    @Override
    public String mangle(String s) {
        return s;
    }
}
