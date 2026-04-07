package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q43 extends Question {

    @Override
    public String getQuestionText() {
        return """
In a using statement, what happens if the body throws an exception?

    using (var fs = File.OpenRead(path)) {
        throw new InvalidOperationException();
    }
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Dispose is skipped because the exception stops execution",
                "Dispose only runs if no exception was thrown",
                "Dispose is called via try/finally even when an exception is thrown",
                "Dispose is deferred to the finalizer instead"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
using desugars to try/finally; Dispose runs in the finally block even when the body throws.
""";
    }
}
