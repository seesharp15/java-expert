package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q54 extends Question {

    @Override
    public String getQuestionText() {
        return """
What kind of equality do record types implement by default?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Reference equality only",
                "Value-based equality on positional properties",
                "Hash code based on object identity",
                "Equality is undefined until overridden"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Records generate value-based Equals/GetHashCode using their defined properties, so two records with same values are equal.
""";
    }
}
