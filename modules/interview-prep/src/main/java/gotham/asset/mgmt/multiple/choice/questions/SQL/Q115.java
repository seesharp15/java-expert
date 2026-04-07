package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q115 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the difference between LEFT JOIN LATERAL and LEFT JOIN in Postgres?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "No difference",
                "LATERAL allows right side to reference left row",
                "LATERAL forces nested loop",
                "LATERAL is only for arrays"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
LATERAL enables correlated subqueries/tables using left columns.
""";
    }
}
