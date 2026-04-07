package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q84 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens to suppressed exceptions in try-with-resources when both the try body and close() throw?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Only close() exception is kept",
                "Only body exception is kept; close() is suppressed",
                "The JVM crashes",
                "Both are lost"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
The body exception is primary; exceptions from close() are added as suppressed to the primary.
""";
    }
}
