package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q72 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is captured in a lambda used inside a loop when the loop variable is reused?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "The current value is copied immediately",
                "The variable itself is captured; late-binding can see final loop value",
                "Each iteration creates a new variable always",
                "Loop variables cannot be captured"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Enhanced for/stream loops reuse the same variable; lambdas capture the variable, so all lambdas may see the last value unless a new effectively-final variable is introduced per iteration.
""";
    }
}
