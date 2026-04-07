package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q50 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is true about an iterator method using yield return?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "The sequence is fully materialized when the method is called",
                "Execution is deferred until the sequence is enumerated",
                "yield return forces parallel execution",
                "It cannot throw exceptions"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Iterators are lazy; the method runs on demand during enumeration, not when the enumerable is created.
""";
    }
}
