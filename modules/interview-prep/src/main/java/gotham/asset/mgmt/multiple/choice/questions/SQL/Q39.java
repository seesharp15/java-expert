package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL DELETE with subquery referencing the same table.
 */
public class Q39 extends Question {

    @Override
    public String getQuestionText() {
        return """
                How many rows remain after executing this statement?

                    -- Table: t (id INT, val INT)
                    -- Data: (1, 10), (2, 20), (3, 10), (4, 30)

                    DELETE FROM t WHERE val = (SELECT MIN(val) FROM t);

                Assume standard SQL behavior (e.g., PostgreSQL or SQL Server).
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "1",
                "2",
                "3",
                "The query fails because you can't select from a table being deleted"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 2

                - MIN(val) evaluates to 10. The DELETE removes all rows where val = 10, \
                which includes rows with id 1 and id 3. The remaining rows are (2, 20) \
                and (4, 30), so 2 rows remain.
                - "1" is wrong: the DELETE removes ALL rows matching val = 10, not just \
                the first one. Both rows with val = 10 are deleted.
                - "3" is wrong: there are two rows with val = 10, not one. Deleting \
                both leaves 2 rows, not 3.
                - "The query fails" is a notable trap: in MySQL, this would indeed fail \
                with "You can't specify target table 't' for update in FROM clause." \
                However, in standard SQL, PostgreSQL, and SQL Server, this is perfectly \
                valid. The subquery is evaluated before the delete begins.
                """;
    }
}
