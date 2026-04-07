package gotham.asset.mgmt.multiple.choice.questions.dotnet;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q146 extends Question {

    @Override
    public String getQuestionText() {
        return """
How does StringBuilder handle capacity when Append exceeds it?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Throws",
                "Doubles (up to a pattern) the capacity to accommodate growth",
                "Truncates",
                "Switches to char[]"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
StringBuilder grows capacity (roughly doubling) to fit additional data.
""";
    }
}
