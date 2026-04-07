package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q71 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does 'happens-before' guarantee for volatile writes/reads?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Only atomicity, not ordering",
                "Only visibility, not ordering",
                "Visibility and ordering of all preceding writes to other variables",
                "It behaves the same as synchronized on the same monitor"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
A volatile write establishes a happens-before with subsequent reads of that variable, making all prior writes by the writer visible and ordered before the read.
""";
    }
}
