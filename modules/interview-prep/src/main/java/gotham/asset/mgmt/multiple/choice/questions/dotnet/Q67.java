package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q67 extends Question {

    @Override
    public String getQuestionText() {
        return """
Using Parallel.ForEach with an async lambda (without special helpers) results in:
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Parallel.ForEach awaits each async delegate automatically",
                "Async delegates run but are not awaited, leading to fire-and-forget work",
                "Compilation error: async not allowed",
                "It serializes execution"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Parallel.ForEach expects synchronous delegates; an async lambda returns Task which is ignored, so work continues without awaiting.
""";
    }
}
