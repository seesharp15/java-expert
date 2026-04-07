package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q142 extends Question {

    @Override
    public String getQuestionText() {
        return """
How does Task.WhenAny behave with a canceled task?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Ignores canceled tasks",
                "Returns as soon as any task is completed, faulted, or canceled",
                "Throws immediately",
                "Waits for non-canceled"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
WhenAny completes when the first task finishes in any terminal state; caller inspects result status.
""";
    }
}
