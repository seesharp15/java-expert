package gotham.asset.mgmt.multiple.choice.questions.java;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q94 extends Question {

    @Override
    public String getQuestionText() {
        return """
What happens when a record defines its own equals() but not hashCode()?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "hashCode is auto-generated and consistent",
                "hashCode stays auto, now inconsistent with custom equals",
                "Both are auto-regenerated",
                "Compilation fails"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Overriding equals without hashCode breaks the pair contract; record’s auto hashCode remains, leading to inconsistency.
""";
    }
}
