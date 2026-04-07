package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q58 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does the volatile keyword guarantee for a field?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Atomicity for all operations",
                "No other thread can read it",
                "Reads and writes are not reordered across the volatile access",
                "It pins the field in CPU cache"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
volatile prevents certain compiler and CPU reorderings for that field, but does not make compound operations atomic.
""";
    }
}
