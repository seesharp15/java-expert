package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL NOT IN with NULL: three-valued logic causes empty result set.
 */
public class Q27 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given the following table and data:

                    CREATE TABLE t (val INT);
                    -- Data: (1), (2), (NULL), (3)

                What does this query return?

                    SELECT COUNT(*) FROM t WHERE val NOT IN (1, 2, NULL);
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "0",
                "1",
                "2",
                "3"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 0.

                - NOT IN with a NULL in the list ALWAYS returns zero rows. Here is why: \
                "val NOT IN (1, 2, NULL)" is equivalent to "val != 1 AND val != 2 AND \
                val != NULL". In SQL's three-valued logic, any comparison with NULL yields \
                UNKNOWN (not TRUE or FALSE). So "val != NULL" is UNKNOWN for every row. \
                Since TRUE AND UNKNOWN = UNKNOWN, the entire WHERE clause is UNKNOWN for \
                every row, and UNKNOWN rows are excluded from results.
                - "1" is wrong: you might think val = 3 would pass because 3 is not in \
                {1, 2}, but the NULL in the list poisons the entire NOT IN evaluation. \
                3 != NULL is UNKNOWN, making the whole condition UNKNOWN.
                - "2" is wrong: similar reasoning -- even if you ignore the NULL row itself, \
                both val = 3 and the NULL row fail because of the NULL in the comparison list.
                - "3" is wrong: this would assume NOT IN simply checks membership against \
                non-null values {1, 2} and matches everything else. SQL does not skip NULLs \
                in the IN list; they participate in the three-valued logic evaluation.
                """;
    }
}
