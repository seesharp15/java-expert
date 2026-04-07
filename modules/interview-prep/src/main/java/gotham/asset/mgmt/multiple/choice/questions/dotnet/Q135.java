package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q135 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does ConfigureAwait(false) on an ASP.NET Core controller action do?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Nothing; there is no captured context",
                "Forces UI thread",
                "Breaks DI",
                "Causes deadlock"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
ASP.NET Core uses default ExecutionContext without a request context; ConfigureAwait(false) typically has no effect.
""";
    }
}
