package gotham.asset.mgmt.multiple.choice.questions.SQL;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import java.util.*;

/**
 * SQL self-join gotcha: finding employees who share a salary with someone else.
 */
public class Q37 extends Question {

    @Override
    public String getQuestionText() {
        return """
                What names are returned by this query?

                    -- employees: (id, name, salary)
                    -- (1, 'Alice', 50000), (2, 'Bob', 60000), (3, 'Carol', 50000)

                    SELECT DISTINCT a.name
                    FROM employees a, employees b
                    WHERE a.salary = b.salary AND a.id != b.id;
                """;
    }

    @Override
    public List<String> getChoices() {
        return List.of(
                "Alice, Bob, Carol",
                "Alice, Carol",
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
                Correct answer: Alice, Carol

                - This is a self-join that pairs every employee with every other \
                employee who has the same salary but a different id. Alice (id=1, \
                salary=50000) matches Carol (id=3, salary=50000) and vice versa. Bob \
                (salary=60000) has no one else with the same salary, so he never \
                satisfies the WHERE clause. DISTINCT eliminates duplicates, so the \
                result is Alice and Carol.
                - "Alice, Bob, Carol" is wrong: Bob's salary of 60000 is unique. There \
                is no other row where salary = 60000 AND id != 2, so Bob never appears.
                - "Alice" is wrong: the self-join is symmetric. If Alice matches Carol, \
                then Carol also matches Alice. Both appear in the DISTINCT result.
                - "No rows returned" is wrong: Alice and Carol share salary 50000, so \
                the join condition is satisfied for both of them.
                """;
    }
}
