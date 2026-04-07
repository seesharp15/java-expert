package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q138 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does await foreach require on the source?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "IEnumerable",
                "IAsyncEnumerable",
                "Task<IEnumerable>",
                "ParallelQuery"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
await foreach enumerates IAsyncEnumerable<T> using async disposables and MoveNextAsync.
""";
    }
}
