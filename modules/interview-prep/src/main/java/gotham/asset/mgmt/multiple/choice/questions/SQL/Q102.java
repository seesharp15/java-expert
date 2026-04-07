package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q102 extends Question {

    @Override
    public String getQuestionText() {
        return """
Which isolation anomaly can still happen under Read Committed?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Dirty read",
                "Non-repeatable read",
                "Lost update always",
                "Phantom read impossible"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Read Committed forbids dirty reads but allows non-repeatable reads and sometimes lost updates without extra locking.
""";
    }
}
