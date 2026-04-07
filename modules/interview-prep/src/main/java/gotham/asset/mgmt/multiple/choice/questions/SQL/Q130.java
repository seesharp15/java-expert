package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q130 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does GROUPING() function indicate in ROLLUP/CUBE results?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Row count",
                "Whether a column is aggregated to NULL as a super-aggregate",
                "Collation",
                "Bucket number"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
GROUPING(col)=1 means that column is not present (aggregated) in that subtotal row, disambiguating from real NULLs.
""";
    }
}
