package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q48 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does ConfigureAwait(false) do on an awaited Task?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Prevents the continuation from running at all",
                "Forces continuation onto the UI thread",
                "Avoids capturing the current context; continuation may run on a thread-pool thread",
                "Cancels the Task when the context changes"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
ConfigureAwait(false) tells the awaiter not to marshal back to the captured SynchronizationContext, so continuation can run anywhere.
""";
    }
}
