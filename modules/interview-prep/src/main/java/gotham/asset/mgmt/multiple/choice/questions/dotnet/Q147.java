package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q147 extends Question {

    @Override
    public String getQuestionText() {
        return """
What is the effect of declaring an event as public field delegate instead of event?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Same semantics",
                "Allows external overwrite/removal bypassing add/remove, breaking encapsulation",
                "Faster and safe",
                "Auto weak references"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Without 'event', consumers can reassign the delegate, removing other handlers; encapsulation is lost.
""";
    }
}
