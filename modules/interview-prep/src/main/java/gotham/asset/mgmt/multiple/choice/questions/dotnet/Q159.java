package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q159 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does IAsyncDisposable.DisposeAsync return and why must it be awaited?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "void; it is fire-and-forget",
                "ValueTask; async cleanup must be awaited to ensure completion",
                "Task; awaiting is optional",
                "IDisposable"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
DisposeAsync returns ValueTask/Task; awaiting ensures async cleanup completes before proceeding.
""";
    }
}
