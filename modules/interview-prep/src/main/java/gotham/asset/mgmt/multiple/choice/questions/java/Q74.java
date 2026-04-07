package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q74 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens if you call wait() outside of a synchronized block on the same object?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It silently returns false",
                "IllegalMonitorStateException is thrown",
                "The thread blocks but cannot be notified",
                "It deadlocks the JVM"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
wait/notify require owning the monitor. Calling wait() without synchronized on that object throws IllegalMonitorStateException at runtime.
""";
    }
}
