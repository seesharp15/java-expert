package gotham.asset.mgmt.GAM15.answerkey;

import gotham.asset.mgmt.GAM15.Solution;

/**
 * GAM15 Answer - The Pre/Post Increment Nightmare
 *
 * Net effect: a ends at its initial value, b = 2 * initialA, c = 0.
 */
public class Answer extends Solution {

    @Override
    public int[] traceIncrements(int initialA) {
        int a = initialA;

        // b = a++ + --a
        int temp1 = a;  // a++ evaluates to current a
        a++;            // a is now initialA + 1
        a--;            // --a decrements first, a is now initialA
        int temp2 = a;  // --a evaluates to decremented a
        int b = temp1 + temp2;

        // c = ++a - a--
        a++;            // ++a increments first
        int temp3 = a;  // ++a evaluates to incremented a
        int temp4 = a;  // a-- evaluates to current a
        a--;            // a is now back to initialA
        int c = temp3 - temp4;

        return new int[]{a, b, c};
    }
}
