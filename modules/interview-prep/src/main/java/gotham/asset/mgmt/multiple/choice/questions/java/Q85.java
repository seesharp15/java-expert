package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q85 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does ThreadLocal cause in a fixed thread pool if not cleared?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Automatic cleanup on task end",
                "Potential memory leaks because values stick to threads",
                "Garbage collection removes them immediately",
                "ThreadLocal is shared across threads"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
ThreadLocal values live as long as the thread; in pools threads persist so values can leak unless removed.
""";
    }
}
