package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q77 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is true about Class.forName("X", false, loader)?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It always initializes the class",
                "It loads but defers initialization until first active use",
                "It fails without initializing static blocks",
                "It returns null if not initialized"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
The 'initialize' flag false loads/links the class but skips initialization (static blocks) until first active use.
""";
    }
}
