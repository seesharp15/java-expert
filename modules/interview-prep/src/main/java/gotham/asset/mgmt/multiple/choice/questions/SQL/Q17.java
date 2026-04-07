package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL LEFT JOIN with WHERE vs ON filter: WHERE on the right table nullifies the LEFT JOIN.
 */
public class Q17 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Consider these two SQL queries:

                    -- Query A:
                    SELECT *
                    FROM orders o
                    LEFT JOIN customers c ON o.customer_id = c.id
                    WHERE c.status = 'active';

                    -- Query B:
                    SELECT *
                    FROM orders o
                    LEFT JOIN customers c ON o.customer_id = c.id
                                          AND c.status = 'active';

                Which statement is true about their results?
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Both queries return the same results",
                "Query A returns more rows than Query B",
                "Query A effectively becomes an INNER JOIN; Query B preserves all orders",
                "Query B effectively becomes an INNER JOIN; Query A preserves all orders"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: Query A effectively becomes an INNER JOIN; Query B preserves all orders.

                - In Query A, the LEFT JOIN initially preserves all orders, filling customer \
                columns with NULL where there's no match. But then WHERE c.status = 'active' \
                filters out any row where c.status is NULL. This eliminates all non-matching \
                rows, effectively converting the LEFT JOIN into an INNER JOIN.
                - In Query B, the condition c.status = 'active' is part of the ON clause. \
                Non-matching orders still appear with NULL customer columns. The LEFT JOIN \
                behavior is preserved because the filter is applied during the join, not after.
                - "Both return the same results" is wrong: Query A loses unmatched orders.
                - "Query A returns more rows" is wrong: it's the opposite; A is more restrictive.
                - "Query B becomes an INNER JOIN" is wrong: the ON clause filter preserves LEFT JOIN semantics.
                """;
    }
}
