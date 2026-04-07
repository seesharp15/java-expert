package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Short-circuit evaluation with side effects on increment operators.
 */
public class Q07 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the output of the following code?

                    int x = 0;
                    boolean result = (x++ > 0) && (x++ > 1);
                    System.out.println(x + " " + result);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "2 false",
                "1 false",
                "2 true",
                "1 true"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 1 false

                - x++ > 0 : post-increment uses x=0 for comparison. 0 > 0 is false. x becomes 1.
                - Because the left side of && is false, Java short-circuits and NEVER evaluates \
                the right side (x++ > 1). The second increment never happens.
                - x is 1, result is false.
                - "2 false" is wrong: it assumes both sides are evaluated (no short-circuit).
                - "2 true" is wrong: even if both sides ran, 1 > 1 would still be false.
                - "1 true" is wrong: the first condition (0 > 0) is false, so result cannot be true.
                """;
    }
}
