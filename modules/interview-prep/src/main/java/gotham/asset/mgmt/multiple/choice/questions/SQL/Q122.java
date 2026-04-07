package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q122 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is a partial index?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "An index on part of a column",
                "An index built on a subset of rows matching a predicate",
                "A disabled index",
                "A fragmented index"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Partial indexes index rows that satisfy a WHERE predicate.
""";
    }
}
