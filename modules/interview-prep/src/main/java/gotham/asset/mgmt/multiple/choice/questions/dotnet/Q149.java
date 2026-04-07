package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q149 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why might using CancellationTokenSource.CancelAfter repeatedly on same CTS be risky?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It allocates threads",
                "It schedules timers; repeated calls reset timers but may keep disposed tokens alive",
                "It does nothing",
                "It blocks"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
CancelAfter uses timers; frequent resets can create timer churn and keep CTS alive longer.
""";
    }
}
