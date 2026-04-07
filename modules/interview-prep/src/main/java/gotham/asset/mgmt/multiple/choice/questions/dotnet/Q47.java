package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q47 extends Question {

    @Override
    public String getQuestionText() {
        return """
In ASP.NET Core, should you wrap inherently async I/O (e.g., database calls) in Task.Run?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Yes, to offload work to the thread pool",
                "Yes, otherwise async I/O blocks the request thread",
                "No, Task.Run adds overhead; just await the async API",
                "It makes no difference either way"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
Async I/O already frees the thread when awaited; Task.Run just schedules extra work and can hurt scalability.
""";
    }
}
