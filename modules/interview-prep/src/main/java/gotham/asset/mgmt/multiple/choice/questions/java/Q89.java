package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q89 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens if you serialize an object with a newer serialVersionUID than the class reading it?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It upgrades automatically",
                "InvalidClassException is thrown",
                "Fields are ignored silently",
                "It truncates the stream"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Mismatch serialVersionUID between stream and class causes InvalidClassException during deserialization.
""";
    }
}
