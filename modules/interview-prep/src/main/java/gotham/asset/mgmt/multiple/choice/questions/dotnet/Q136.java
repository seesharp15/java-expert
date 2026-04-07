package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q136 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the default behavior of async void exceptions?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Swallowed",
                "Posted to SynchronizationContext and may crash process",
                "Wrapped in AggregateException",
                "Logged automatically"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Unhandled async void exceptions go to the context and can crash the app.
""";
    }
}
