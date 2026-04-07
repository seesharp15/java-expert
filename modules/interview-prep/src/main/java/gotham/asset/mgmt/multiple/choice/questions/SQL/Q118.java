package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q118 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does ON COMMIT DROP do for a temp table?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Drops at session end",
                "Drops at commit of the current transaction",
                "Drops immediately",
                "Never drops"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
ON COMMIT DROP removes the temporary table when the transaction commits.
""";
    }
}
