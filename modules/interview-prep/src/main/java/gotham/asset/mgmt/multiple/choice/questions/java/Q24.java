package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Compound assignment operator precedence: left operand is evaluated before the right.
 */
public class Q24 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the value of x after this code executes?

                    int x = 1;
                    x += x += x += 1;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "4",
                "6",
                "8",
                "Compilation error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 4.

                - Compound assignment operators (+=) are right-associative, so the expression \
                parses as x += (x += (x += 1)). However, the JLS (Section 15.26.2) specifies \
                that the left-hand operand of a compound assignment is evaluated FIRST to \
                determine the variable AND its current value is saved BEFORE the right-hand \
                side is evaluated. Trace:
                  1. Outer +=: save current value of x = 1, then evaluate RHS.
                  2. Middle +=: save current value of x = 1, then evaluate RHS.
                  3. Inner +=: save current value of x = 1. Compute 1 + 1 = 2. Assign x = 2. \
                Result is 2.
                  4. Middle +=: uses saved value 1 + result 2 = 3. Assign x = 3. Result is 3.
                  5. Outer +=: uses saved value 1 + result 3 = 4. Assign x = 4. Result is 4.
                - "6" is wrong: this would result from incorrectly re-reading x's current \
                (mutated) value at each level instead of using the saved value.
                - "8" is wrong: this assumes each += doubles the value, ignoring that the \
                left-hand operand is captured before the right-hand side mutates x.
                - "Compilation error" is wrong: chained compound assignment is legal Java; \
                the expression is well-formed and the types are compatible.
                """;
    }
}
