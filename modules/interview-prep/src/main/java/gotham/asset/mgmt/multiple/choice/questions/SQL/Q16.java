package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL GROUP BY with WHERE filtering before aggregation vs HAVING after.
 */
public class Q16 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given the following table and data:

                    CREATE TABLE orders (id INT, customer_id INT, amount INT);
                    -- (1, 1, 100), (2, 1, 200), (3, 2, 150), (4, 2, 50), (5, 3, 300)

                What is the result of this query?

                    SELECT customer_id, SUM(amount) AS total
                    FROM orders
                    WHERE amount > 50
                    GROUP BY customer_id
                    HAVING total > 200;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "customer_id=1 (total=300), customer_id=2 (total=200), customer_id=3 (total=300)",
                "customer_id=1 (total=300), customer_id=3 (total=300)",
                "customer_id=1 (total=300), customer_id=2 (total=150), customer_id=3 (total=300)",
                "customer_id=3 (total=300)"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 1;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: customer_id=1 (total=300), customer_id=3 (total=300)

                Execution order matters:
                1. WHERE amount > 50 filters rows BEFORE grouping:
                   - Customer 1: keeps (100) and (200) -> both pass
                   - Customer 2: keeps (150), removes (50) -> only 150 remains
                   - Customer 3: keeps (300) -> passes
                2. GROUP BY customer_id then aggregates:
                   - Customer 1: SUM = 100 + 200 = 300
                   - Customer 2: SUM = 150
                   - Customer 3: SUM = 300
                3. HAVING total > 200 filters AFTER grouping:
                   - Customer 1: 300 > 200 -> included
                   - Customer 2: 150 > 200 -> excluded
                   - Customer 3: 300 > 200 -> included
                - The first choice is wrong: it ignores the WHERE filter on customer 2's 50-amount row \
                and incorrectly includes customer 2 with total=200.
                - The third choice is wrong: customer 2's total of 150 does NOT pass HAVING > 200.
                - The fourth choice is wrong: customer 1 also has total=300, which passes HAVING.
                """;
    }
}
