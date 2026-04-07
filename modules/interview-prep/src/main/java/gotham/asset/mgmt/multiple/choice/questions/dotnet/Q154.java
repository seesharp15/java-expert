package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q154 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does TaskCreationOptions.LongRunning hint do?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Creates a dedicated thread instead of pool thread",
                "Uses IOCP",
                "Disables cancellation",
                "Forces inlining"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
LongRunning suggests scheduler use a dedicated thread (Thread creation in default scheduler).
""";
    }
}
