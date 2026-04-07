package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL NULL comparison trap: NULL != value evaluates to NULL/UNKNOWN, not TRUE.
 */
public class Q15 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given the following table and data:

                    CREATE TABLE employees (id INT, name VARCHAR, manager_id INT);
                    -- (1, 'Alice', NULL)
                    -- (2, 'Bob',   1)
                    -- (3, 'Carol', 1)
                    -- (4, 'Dave',  2)

                What names are returned by this query?

                    SELECT name FROM employees WHERE manager_id != 1;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Alice, Dave",
                "Dave",
                "Alice",
                "No rows returned"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: Dave

                - In SQL, any comparison with NULL yields NULL (UNKNOWN), not TRUE or FALSE.
                - For Alice: manager_id is NULL, so NULL != 1 evaluates to NULL (UNKNOWN). \
                The WHERE clause only includes rows where the condition is TRUE, so Alice is excluded.
                - For Bob: manager_id = 1, so 1 != 1 is FALSE. Excluded.
                - For Carol: manager_id = 1, so 1 != 1 is FALSE. Excluded.
                - For Dave: manager_id = 2, so 2 != 1 is TRUE. Included.
                - "Alice, Dave" is wrong: NULL != 1 does NOT evaluate to TRUE; it evaluates to NULL.
                - "Alice" is wrong: same NULL comparison issue, and Alice's condition is not TRUE.
                - "No rows returned" is wrong: Dave's manager_id is 2, which satisfies != 1.
                - To include Alice, you would need: WHERE manager_id != 1 OR manager_id IS NULL.
                """;
    }
}
