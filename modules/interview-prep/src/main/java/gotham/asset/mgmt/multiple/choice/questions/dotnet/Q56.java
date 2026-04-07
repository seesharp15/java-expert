package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q56 extends Question {

    @Override
    public String getQuestionText() {
        return """
Thread.Sleep(1000) vs Task.Delay(1000):
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Both block a thread for 1 second",
                "Sleep blocks a thread; Delay returns a Task that completes without blocking",
                "Delay blocks while Sleep does not",
                "Neither impacts thread scheduling"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Thread.Sleep blocks the calling thread; Task.Delay schedules a timer and the awaiting thread can return to the pool.
""";
    }
}
