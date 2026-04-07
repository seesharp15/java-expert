package gotham.asset.mgmt.multiple.choice.questions.debugging.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D40 extends Question {

    @Override
    public String getQuestionText() {
        return """
Tables A={1,2}, B={2,2}.
Query: SELECT * FROM A UNION ALL SELECT * FROM B;
How many rows?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "3",
                "4",
                "2",
                "Error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
UNION ALL does not de-duplicate: rows = 2 from A + 2 from B = 4.
""";
    }
}
