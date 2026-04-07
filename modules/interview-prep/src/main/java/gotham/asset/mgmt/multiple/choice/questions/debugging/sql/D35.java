package gotham.asset.mgmt.multiple.choice.questions.debugging.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D35 extends Question {

    @Override
    public String getQuestionText() {
        return """
Rows (ordered by id): id 1 price 10, id 2 price 20, id 3 price 30.
Query: SELECT id, SUM(price) OVER () AS total, SUM(price) OVER (ORDER BY id) AS running
What is running value at id=2?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "30",
                "20",
                "60",
                "10"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Running sum ordered by id: after rows 1+2 -> 30.
""";
    }
}
