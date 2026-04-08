package gotham.asset.mgmt.multiple.choice.temp.multiplechoicedupes.sql;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

public class D43 extends Question {

    @Override
    public String getQuestionText() {
        return """
Query (PostgreSQL with standard SQL):
SELECT dept, salary, COUNT(*) FROM employees GROUP BY dept;
What is the result?
""";
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Error: column salary must appear in GROUP BY or be aggregated",
                "Returns any salary per dept",
                "Returns max salary per dept",
                "Returns NULL for salary"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
Non-aggregated column not in GROUP BY causes a SQL error in standards-compliant mode.
""";
    }
}
