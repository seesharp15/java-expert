package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q110 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is a covering index?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "An index that includes all columns needed by a query",
                "An index on a view",
                "A clustered index",
                "An index with NULLs"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
If all referenced columns are in the index (key or included), the engine can satisfy the query without touching the table.
""";
    }
}
