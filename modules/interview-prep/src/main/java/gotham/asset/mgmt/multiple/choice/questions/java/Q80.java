package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q80 extends Question {

    @Override
    public String getQuestionText() {
        return """
Why is double-checked locking safe only with volatile on the singleton instance?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "volatile speeds up access",
                "volatile prevents reordering that could expose a partially constructed object",
                "volatile guarantees uniqueness",
                "volatile blocks other threads entirely"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Without volatile, writes of the reference can be reordered before constructor completes. volatile enforces ordering/visibility so other threads don't see a half-constructed instance.
""";
    }
}
