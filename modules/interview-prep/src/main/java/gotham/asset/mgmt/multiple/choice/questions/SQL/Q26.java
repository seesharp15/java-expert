package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL BETWEEN is inclusive on both ends.
 */
public class Q26 extends Question {

    @Override
    public String getQuestionText() {
        return """
                Given the following table and data:

                    CREATE TABLE products (id INT, price DECIMAL);
                    -- Data: (1, 10.00), (2, 20.00), (3, 30.00), (4, 40.00), (5, 50.00)

                What does this query return?

                    SELECT COUNT(*) FROM products WHERE price BETWEEN 20 AND 40;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "1",
                "2",
                "3",
                "5"
        );
    }

    @Override
    public int getCorrectAnswerIndex() {
        return 2;
    }

    @Override
    public String getExplanation() {
        return """
                Correct answer: 3.

                - SQL BETWEEN is INCLUSIVE on both endpoints. The expression \
                "price BETWEEN 20 AND 40" is equivalent to "price >= 20 AND price <= 40". \
                The matching rows are: (2, 20.00), (3, 30.00), and (4, 40.00) -- three rows.
                - "1" is wrong: this would assume BETWEEN is exclusive on both ends \
                (price > 20 AND price < 40), which would match only 30.00. But BETWEEN \
                includes the boundary values.
                - "2" is wrong: this would assume BETWEEN is exclusive on one end (e.g., \
                price > 20 AND price <= 40 or price >= 20 AND price < 40), matching only \
                two of the three boundary/interior values. BETWEEN is inclusive on BOTH ends.
                - "5" is wrong: this would mean all rows match, which ignores the filtering \
                entirely. Rows with price 10.00 and 50.00 are outside the 20-to-40 range.
                """;
    }
}
