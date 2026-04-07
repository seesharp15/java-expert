package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q156 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does ThreadPool.SetMinThreads affect?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Maximum threads",
                "Minimum worker/IOCP threads kept available before queuing",
                "Priority",
                "Affinity"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Sets minimum number of threads the pool tries to keep available before queuing work.
""";
    }
}
