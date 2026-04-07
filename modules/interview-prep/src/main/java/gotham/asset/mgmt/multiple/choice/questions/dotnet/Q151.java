package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q151 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does Enumerable.SequenceEqual do when sequences have different lengths?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Throws",
                "Returns false immediately once length difference is observed",
                "Ignores extras",
                "Truncates"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
It compares element by element; if one ends earlier, returns false.
""";
    }
}
