package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q53 extends Question {

    @Override
    public String getQuestionText() {
        return """
For a non-nullable enum, what happens if a switch expression is missing a case?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "It compiles and returns default",
                "It compiles but throws at runtime",
                "It fails to compile because the expression is not exhaustive",
                "It silently matches the first case"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
Switch expressions must be exhaustive; missing cases on a non-nullable enum produce a compile-time error.
""";
    }
}
