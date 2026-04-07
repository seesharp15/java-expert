package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q148 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens when awaiting Task.Run in a single-threaded SynchronizationContext (e.g., WinForms)?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Continuation always stays on worker thread",
                "Continuation posts back to the context by default, avoiding deadlock",
                "It deadlocks always",
                "It throws"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Await captures context; after Task.Run completes on pool thread, continuation posts to original context (UI thread).
""";
    }
}
