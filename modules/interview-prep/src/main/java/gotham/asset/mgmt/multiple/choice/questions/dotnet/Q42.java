package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q42 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens if an exception is thrown from an async void event handler on the UI thread?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It is swallowed silently",
                "It flows to the SynchronizationContext and can crash the app",
                "It becomes an AggregateException on the caller Task",
                "It is ignored when ConfigureAwait(false) is used"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
async void exceptions are not captured in a Task; they are posted to the current SynchronizationContext and can terminate the process if unhandled.
""";
    }
}
