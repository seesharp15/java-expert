package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q90 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why might ReentrantLock fair=true reduce throughput?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It uses spin locks",
                "Fairness enforces FIFO acquisition causing more context switches",
                "It disallows reentrance",
                "It blocks interruptions"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Fair locks avoid barging, increasing contention management and context switching, lowering throughput.
""";
    }
}
