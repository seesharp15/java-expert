package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q96 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why should finalize() be avoided?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It prevents GC",
                "It is unpredictable, can resurrect objects, and is deprecated in favor of cleaners",
                "It runs immediately on gc",
                "It guarantees resource release"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
finalize is deprecated; scheduling is unpredictable, can resurrect objects, and delays reclamation; prefer try-with-resources/cleaners.
""";
    }
}
