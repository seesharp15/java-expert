package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q91 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the visibility guarantee of final fields after construction?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "None; need volatile",
                "Properly constructed objects publish final fields safely to other threads",
                "Final fields are mutable",
                "Final fields are cached forever"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
The Java Memory Model gives special initialization safety: final fields are visible after the constructor finishes without extra synchronization.
""";
    }
}
