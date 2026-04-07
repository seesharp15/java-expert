package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q66 extends Question {

    @Override
    public String getQuestionText() {
        return """
Task.WhenAll with one faulted Task and one successful Task:
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Stops both immediately and throws",
                "Returns a faulted Task after all inputs complete, throwing the first exception when awaited",
                "Ignores faults by default",
                "Only completes when all tasks succeed"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
WhenAll waits for all tasks; if any fault, the returned Task faults after all finish, aggregating exceptions.
""";
    }
}
