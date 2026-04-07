package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q127 extends Question {

    @Override
    public String getQuestionText() {
        return """
How does MERGE handle NOT MATCHED BY SOURCE rows with DELETE clause?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Deletes rows in target absent from source",
                "Deletes rows in source",
                "No effect",
                "Raises error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
NOT MATCHED BY SOURCE WHEN NOT MATCHED THEN DELETE removes target rows not present in source (semantics vary slightly by DB).
""";
    }
}
