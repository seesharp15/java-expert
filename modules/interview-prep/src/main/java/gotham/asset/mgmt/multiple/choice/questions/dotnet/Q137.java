package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q137 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why is using Task.Run inside ASP.NET Core controller usually discouraged for CPU-bound work?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It blocks threads",
                "ThreadPool is shared with request handling; offloading CPU work can hurt throughput",
                "It uses UI threads",
                "It changes culture"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Task.Run consumes pool threads; heavy CPU work may starve the same pool handling requests.
""";
    }
}
