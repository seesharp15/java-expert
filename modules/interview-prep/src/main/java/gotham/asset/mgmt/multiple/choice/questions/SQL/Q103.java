package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q103 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the effect of SELECT ... FOR UPDATE SKIP LOCKED?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Blocks until row free",
                "Raises error on locked rows",
                "Skips locked rows and returns others immediately",
                "Ignores locks and may dirty read"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
SKIP LOCKED skips rows currently locked, useful for work queues without waiting.
""";
    }
}
