package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q59 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens when two threads lock on different object instances?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "They still synchronize because lock uses type",
                "Deadlock is guaranteed",
                "They do not synchronize; each lock is independent",
                "The runtime throws an exception"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
lock/Monitor synchronizes per object reference. Different instances mean no mutual exclusion.
""";
    }
}
