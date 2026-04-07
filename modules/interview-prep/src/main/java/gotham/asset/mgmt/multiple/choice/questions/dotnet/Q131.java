package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q131 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the default SynchronizationContext in a .NET console app?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "WPF context",
                "ASP.NET context",
                "Usually null, so continuations go to ThreadPool",
                "UI thread dispatcher"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
Console apps typically have no SynchronizationContext; awaits resume on ThreadPool unless overridden.
""";
    }
}
