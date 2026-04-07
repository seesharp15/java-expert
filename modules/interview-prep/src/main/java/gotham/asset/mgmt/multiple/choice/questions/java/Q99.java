package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q99 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why might parallelStream().forEachOrdered(...) be slower than forEach(...) ?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It uses recursion",
                "forEachOrdered preserves encounter order requiring extra coordination",
                "It changes parallelism",
                "It disables SIMD"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
forEachOrdered must preserve order, adding synchronization/barriers in parallel execution, often reducing speed.
""";
    }
}
