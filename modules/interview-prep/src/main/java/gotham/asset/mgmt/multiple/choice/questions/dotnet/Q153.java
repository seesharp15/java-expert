package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q153 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is captured by closures over foreach iteration variable in C# 5 vs C# 4?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Same variable reused in C# 4 causing late binding; C# 5 creates a new variable per iteration",
                "Always new variable",
                "Never captured",
                "Only value types"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
C# 5 changed foreach capture semantics to create a fresh variable per iteration to avoid late-binding bug present before.
""";
    }
}
