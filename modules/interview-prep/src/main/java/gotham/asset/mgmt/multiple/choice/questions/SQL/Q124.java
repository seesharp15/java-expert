package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q124 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does NVL/COALESCE pick when multiple NULLs and values exist?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Returns NULL if any NULL",
                "Returns first non-NULL in argument order",
                "Returns last argument",
                "Random"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
COALESCE/NVL returns the first non-NULL argument.
""";
    }
}
