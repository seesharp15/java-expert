package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q108 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens if you reference a CTE recursively without an explicit termination?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Runs forever",
                "Fails to compile",
                "May loop until max recursion/stack limit and then error",
                "Returns empty set"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
Recursive CTEs need termination; otherwise they keep generating rows until hitting a recursion limit error.
""";
    }
}
