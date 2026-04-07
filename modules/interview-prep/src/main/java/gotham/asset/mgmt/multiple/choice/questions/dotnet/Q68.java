package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q68 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens when you enumerate the same LINQ query twice without materializing it?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "The second enumeration reuses cached results",
                "The query executes again each time it's enumerated",
                "It throws InvalidOperationException",
                "It returns an empty sequence the second time"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
LINQ queries are lazy; unless materialized (ToList/ToArray), each enumeration re-executes the query pipeline.
""";
    }
}
