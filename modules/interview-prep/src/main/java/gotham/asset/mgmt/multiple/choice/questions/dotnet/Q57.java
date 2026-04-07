package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q57 extends Question {

    @Override
    public String getQuestionText() {
        return """
How does a CancellationToken stop an async operation?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Cancellation automatically aborts the thread",
                "The runtime cancels all awaited Tasks globally",
                "The operation must observe the token; cooperative cancellation typically throws OperationCanceledException",
                "It only works for Task.Run, not async I/O"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
CancellationToken is cooperative; APIs must check the token and throw OperationCanceledException or stop work when signaled.
""";
    }
}
