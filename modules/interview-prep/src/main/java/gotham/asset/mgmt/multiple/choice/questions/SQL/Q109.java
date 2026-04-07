package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q109 extends Question {

    @Override
    public String getQuestionText() {
        return """
How does COUNT(column) treat NULLs?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Counts them",
                "Ignores NULLs",
                "Raises error",
                "Counts only NULLs"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
COUNT(col) counts non-NULL rows; COUNT(*) counts all.
""";
    }
}
