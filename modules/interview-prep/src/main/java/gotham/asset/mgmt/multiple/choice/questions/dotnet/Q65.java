package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q65 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does FirstOrDefault() return on an empty IEnumerable<int>?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "null",
                "Throws InvalidOperationException",
                "0 (default(int))",
                "-1"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
For value types, FirstOrDefault returns default(T); for int that is 0.
""";
    }
}
