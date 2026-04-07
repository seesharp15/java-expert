package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q107 extends Question {

    @Override
    public String getQuestionText() {
        return """
Which set operator preserves duplicates?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "UNION",
                "INTERSECT",
                "EXCEPT",
                "UNION ALL"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 3;
    }

    @Override
    public String getExplanation() {
        return """
UNION ALL does not eliminate duplicates; others are distinct by default.
""";
    }
}
