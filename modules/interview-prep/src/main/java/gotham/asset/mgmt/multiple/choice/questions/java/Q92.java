package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q92 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does MethodHandles.lookup().findPrivate access depend on?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Runtime stack only",
                "Lookup object’s lookup class and its access modes",
                "Caller class always",
                "Module graph is ignored"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Access checks use the Lookup object (typically caller via lookup()) and module accessibility; findPrivate is allowed only for the lookup class.
""";
    }
}
