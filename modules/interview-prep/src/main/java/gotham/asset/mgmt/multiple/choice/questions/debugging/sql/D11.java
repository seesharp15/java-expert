package gotham.asset.mgmt.multiple.choice.questions.debugging.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D11 extends Question {

    @Override
    public String getQuestionText() {
        return """
Query: SELECT * FROM accounts WHERE status != 'active';
Table rows:
 1) id=1, status='active'
 2) id=2, status='inactive'
 3) id=3, status=NULL
How many rows are returned?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "1 row (id=2)",
                "2 rows (id=2,3)",
                "0 rows",
                "All rows"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Comparison with NULL yields UNKNOWN, so row 3 is excluded; only id=2 matches.
""";
    }
}
