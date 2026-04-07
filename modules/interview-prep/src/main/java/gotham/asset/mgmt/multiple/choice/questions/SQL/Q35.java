package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL COUNT(*) vs COUNT(column) with NULLs.
 */
public class Q35 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What is the result of this query?

                    -- Table: t (id INT, val INT)
                    -- Data: (1, 10), (2, NULL), (3, 30), (4, NULL)

                    SELECT COUNT(*), COUNT(val) FROM t;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "4, 4",
                "4, 2",
                "2, 2",
                "4, 0"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 4, 2

                - COUNT(*) counts all rows regardless of NULL values. There are 4 rows \
                in the table, so COUNT(*) returns 4. COUNT(val) counts only rows where \
                val is NOT NULL. Only rows with id 1 (val=10) and id 3 (val=30) have \
                non-NULL val, so COUNT(val) returns 2.
                - "4, 4" is wrong: COUNT(column) does NOT count NULLs. This is the \
                most common mistake -- confusing COUNT(*) with COUNT(column).
                - "2, 2" is wrong: COUNT(*) counts all rows, not just non-NULL ones. \
                It never skips rows based on column values.
                - "4, 0" is wrong: COUNT(val) counts non-NULL values, not NULL values. \
                There are 2 non-NULL values, not 0.
                """;
    }
}
