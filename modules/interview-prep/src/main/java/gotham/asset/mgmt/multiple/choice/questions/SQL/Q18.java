package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL COALESCE and NULL arithmetic: NULL + anything = NULL.
 */
public class Q18 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the result of the following SQL expression?

                    SELECT COALESCE(NULL + 1, 0) + COALESCE(NULL, 5);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "6",
                "5",
                "1",
                "NULL"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 5

                Step by step:
                1. NULL + 1 = NULL (any arithmetic with NULL yields NULL).
                2. COALESCE(NULL, 0) = 0 (first non-NULL value is 0).
                3. COALESCE(NULL, 5) = 5 (first non-NULL value is 5).
                4. 0 + 5 = 5.
                - 6 is wrong: it assumes NULL + 1 = 1, then COALESCE(1, 0) = 1, giving 1 + 5 = 6. \
                But NULL + 1 is NULL, not 1.
                - 1 is wrong: this would require COALESCE(NULL, 0) to be 1, which it isn't.
                - NULL is wrong: both COALESCE calls return non-NULL values, so the addition \
                produces a non-NULL result.
                """;
    }
}
