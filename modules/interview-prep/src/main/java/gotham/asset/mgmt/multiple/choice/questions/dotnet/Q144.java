package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q144 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does async Task Main() require to compile?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Nothing special",
                "C# 7.1 or later",
                "A custom attribute",
                "Top-level statements"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Async Main returning Task/Task<int> is supported starting C# 7.1.
""";
    }
}
