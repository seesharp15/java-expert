package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q52 extends Question {

    @Override
    public String getQuestionText() {
        return """
Does Dictionary<TKey, TValue> guarantee insertion order?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Yes, insertion order is guaranteed",
                "Yes, only in .NET Core",
                "No, the type does not guarantee ordering",
                "It is always sorted by key"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
Even though current implementations preserve insertion order, the contract does not guarantee it; code should not rely on ordering.
""";
    }
}
