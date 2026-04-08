package gotham.asset.mgmt.multiple.choice.temp.multiplechoicedupes.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D46 extends Question {

    @Override
    public String getQuestionText() {
        return """
Row created_at='2023-01-01 12:00:00'.
Query: WHERE created_at BETWEEN '2023-01-01' AND '2023-01-01';
Does the row match?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Yes, BETWEEN is inclusive and time is truncated to midnight",
                "No, because time is after end bound",
                "Always matches",
                "Syntax error"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
Literal date casts to midnight; upper bound at 00:00 excludes 12:00 row.
""";
    }
}
