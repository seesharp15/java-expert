package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q70 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does await using (var resource = asyncDisposable) do?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Calls Dispose synchronously",
                "Queues disposal on the finalizer",
                "Awaits IAsyncDisposable.DisposeAsync when exiting the scope",
                "Suppresses disposal entirely"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
await using invokes DisposeAsync on IAsyncDisposable and awaits it when the scope exits.
""";
    }
}
