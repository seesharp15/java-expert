package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class Q101 extends Question {

    @Override
    public String getQuestionText() {
        return """
What does ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW exclude?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Current row",
                "All rows",
                "Future rows",
                "Partition rows with NULLs"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
That frame includes all rows from start of partition up to and including current row; it excludes future rows.
""";
    }
}
