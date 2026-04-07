package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q105 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does CROSS APPLY do compared to CROSS JOIN in SQL Server?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Identical behavior",
                "APPLY allows referencing outer columns in the right table expression",
                "APPLY is only for full joins",
                "APPLY forces hash join"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
APPLY is like LATERAL; the right side can reference left rows, enabling correlated table-valued functions.
""";
    }
}
