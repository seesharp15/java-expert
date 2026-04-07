package gotham.asset.mgmt.multiple.choice.questions.debugging.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D39 extends Question {

    @Override
    public String getQuestionText() {
        return """
MySQL (ONLY_FULL_GROUP_BY disabled):
SELECT name FROM users GROUP BY country ORDER BY age;
What can happen?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Non-deterministic ordering because age is not grouped",
                "Error",
                "Sorted by country",
                "Age treated as NULL"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Without ONLY_FULL_GROUP_BY, MySQL allows it but ordering column not in group leads to indeterminate value/order.
""";
    }
}
