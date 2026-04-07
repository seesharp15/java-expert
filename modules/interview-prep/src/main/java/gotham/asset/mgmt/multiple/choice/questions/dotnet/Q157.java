package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q157 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is true about AsyncLocal<T>?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Flows with ExecutionContext across async awaits",
                "Local to a thread only",
                "Not inherited",
                "Static only"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
AsyncLocal values flow with ExecutionContext through awaits, unlike ThreadLocal.
""";
    }
}
