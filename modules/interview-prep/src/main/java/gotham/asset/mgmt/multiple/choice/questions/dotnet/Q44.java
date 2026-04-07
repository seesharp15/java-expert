package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q44 extends Question {

    @Override
    public String getQuestionText() {
        return """
Consider an async method with no await:

    async Task<int> Foo() { return 42; }
    var t = Foo();
    Console.WriteLine(t.IsCompleted);

What is printed?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "False, because async always schedules work",
                "True; the method ran synchronously (with a compiler warning about no awaits)",
                "It does not compile without an await",
                "True, but only after awaiting t"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Without awaits the method executes synchronously, returning a completed Task and emitting compiler warning CS1998.
""";
    }
}
