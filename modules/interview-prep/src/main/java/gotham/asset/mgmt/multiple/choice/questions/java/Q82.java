package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q82 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the result of Collections.unmodifiableList(new ArrayList<>(list)).add(x)?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Adds element to underlying list",
                "Throws UnsupportedOperationException",
                "Creates new list with element",
                "Returns false"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
The unmodifiable wrapper throws UnsupportedOperationException on mutators; underlying list is not exposed.
""";
    }
}
