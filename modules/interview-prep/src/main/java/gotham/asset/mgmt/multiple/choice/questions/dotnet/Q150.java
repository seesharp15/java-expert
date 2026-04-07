package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q150 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the behavior of lock(this) inside a public instance method?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Safe",
                "Exposes the lock object to callers leading to potential deadlocks",
                "Equivalent to Monitor.Enter(new object())",
                "Prevents reentrancy"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Locking on 'this' is risky because external code can also lock the same object, causing deadlocks.
""";
    }
}
