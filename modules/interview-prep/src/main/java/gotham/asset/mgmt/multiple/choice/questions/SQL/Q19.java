package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL UNION vs UNION ALL: UNION deduplicates (including NULLs), UNION ALL does not.
 */
public class Q19 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given two single-column tables:

                    Table A (val): 1, NULL, 3
                    Table B (val): 1, NULL, 4

                How many rows does each query return?

                    -- Query 1:
                    SELECT val FROM A UNION SELECT val FROM B;

                    -- Query 2:
                    SELECT val FROM A UNION ALL SELECT val FROM B;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Query 1: 4 rows, Query 2: 6 rows",
                "Query 1: 5 rows, Query 2: 6 rows",
                "Query 1: 4 rows, Query 2: 5 rows",
                "Query 1: 6 rows, Query 2: 6 rows"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 0;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: Query 1: 4 rows, Query 2: 6 rows

                - UNION removes duplicates. The combined set is {1, 1, NULL, NULL, 3, 4}. \
                UNION treats NULLs as equal for deduplication purposes (unlike comparisons). \
                After removing duplicates: {1, NULL, 3, 4} = 4 rows.
                - UNION ALL keeps all rows: {1, NULL, 3, 1, NULL, 4} = 6 rows.
                - "5 rows, 6 rows" is wrong: it assumes NULL values are not deduplicated by UNION, \
                but UNION does treat NULL = NULL for DISTINCT purposes.
                - "4 rows, 5 rows" is wrong: UNION ALL never removes duplicates; all 6 rows remain.
                - "6 rows, 6 rows" is wrong: UNION always removes duplicates.
                """;
    }
}
