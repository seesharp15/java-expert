package gotham.asset.mgmt.multiple.choice.temp.multiplechoicedupes.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D22 extends Question {

    @Override
    public String getQuestionText() {
        return """
Query:
SELECT o.id, c.name
FROM orders o
LEFT JOIN customers c ON o.cust_id = c.id
WHERE c.country = 'US';
What happens to orders with no matching customer?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "They are filtered out, result acts like INNER JOIN",
                "They appear with NULL customer",
                "Query error",
                "They duplicate"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
WHERE on right-table column removes NULL-joined rows, turning it into an inner join.
""";
    }
}
