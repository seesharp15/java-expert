package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q83 extends Question {

    @Override
    public String getQuestionText() {
        return """
How does synchronized(this) inside a public method affect reentrancy?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It disallows reentry from same thread",
                "Reentrant; same thread can reenter the monitor without blocking",
                "Creates a new lock instance each time",
                "Deadlocks if called twice"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Java monitors are reentrant; a thread holding the monitor can enter it again.
""";
    }
}
