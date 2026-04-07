package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * Post-increment and pre-increment in a complex expression.
 */
public class Q03 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the value of result after this code executes?

                    int a = 5;
                    int result = a++ + ++a + a++;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "18",
                "19",
                "20",
                "21"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 19

                Trace step by step (left to right evaluation):
                1. a++ : use current value 5, then a becomes 6.
                2. ++a : increment a to 7 first, then use 7.
                3. a++ : use current value 7, then a becomes 8.
                4. result = 5 + 7 + 7 = 19. (a is now 8, but result is 19.)
                - 18 is wrong: this comes from incorrectly computing 5 + 6 + 7, forgetting that \
                ++a skips 6 and jumps to 7.
                - 20 is wrong: this comes from using 6 for the second operand instead of 7.
                - 21 is wrong: this comes from using the post-increment value (8) for the third operand.
                """;
    }
}
