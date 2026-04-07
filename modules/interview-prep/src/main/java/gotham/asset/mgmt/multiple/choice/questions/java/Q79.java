package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q79 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does Optional.orElseThrow() do when Optional is empty?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Returns null",
                "Throws NoSuchElementException",
                "Throws IllegalStateException",
                "Returns an Optional empty"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
orElseThrow with no args throws NoSuchElementException when empty.
""";
    }
}
