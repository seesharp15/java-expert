package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL aggregate with no GROUP BY: mixing aggregate and non-aggregate columns.
 */
public class Q29 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given the following table and data:

                    CREATE TABLE sales (id INT, amount INT);
                    -- Data: (1, 100), (2, 200), (3, 300)

                What is the result of this query in standard SQL?

                    SELECT id, MAX(amount) FROM sales;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Returns (3, 300)",
                "Returns (1, 300)",
                "Error: id must be in GROUP BY or an aggregate function",
                "Returns all three rows with a MAX column appended"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: Error: id must be in GROUP BY or an aggregate function.

                - In standard SQL (and MySQL with ONLY_FULL_GROUP_BY enabled, which is the \
                default since 5.7.5), selecting a non-aggregated column (id) alongside an \
                aggregate function (MAX) without a GROUP BY clause is an error. The database \
                cannot determine which id value to return because MAX(amount) collapses all \
                rows into a single result, but id has three different values.
                - "Returns (3, 300)" is wrong: while it seems logical that the id associated \
                with the maximum amount is 3, SQL aggregation does not work this way. MAX() \
                only computes the maximum of the amount column; it does not identify or \
                preserve the corresponding row's other column values.
                - "Returns (1, 300)" is wrong: this would imply MySQL's legacy non-strict \
                behavior where it returns an arbitrary/indeterminate id. This is not valid \
                in standard SQL.
                - "Returns all three rows with a MAX column appended" is wrong: that would \
                be the behavior of a window function (MAX(amount) OVER()), not a plain \
                aggregate. A plain aggregate with no GROUP BY collapses the result to a \
                single row.
                """;
    }
}
